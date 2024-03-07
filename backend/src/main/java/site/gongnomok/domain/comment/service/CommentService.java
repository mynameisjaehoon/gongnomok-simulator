package site.gongnomok.domain.comment.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.gongnomok.domain.comment.dto.CommentCreateResponse;
import site.gongnomok.domain.comment.dto.CommentCreateServiceDto;
import site.gongnomok.domain.comment.dto.CommentResponse;
import site.gongnomok.domain.comment.exception.CannotFindItemCommentException;
import site.gongnomok.domain.comment.repository.CommentQueryRepository;
import site.gongnomok.domain.comment.repository.CommentJpaRepository;
import site.gongnomok.domain.item.repository.ItemRepository;
import site.gongnomok.global.entity.Comment;
import site.gongnomok.global.entity.Item;
import site.gongnomok.global.util.SecurityUtil;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final ItemRepository itemRepository;
    private final CommentQueryRepository commentQueryRepository;
    private final CommentJpaRepository commentJpaRepository;

    public CommentCreateResponse createComment(
        final CommentCreateServiceDto createDto,
        final Long itemId
    ) {

        String encryptedPassword = SecurityUtil.encryptSha256(createDto.getPassword());

        Item findItem = itemRepository.findById(itemId).orElseThrow(() -> new CannotFindItemCommentException(itemId));
        Comment newComment = Comment.of(createDto.getName(), encryptedPassword, createDto.getContent());

        newComment.changeItem(findItem);
        Comment savedComment = commentJpaRepository.save(newComment);

        return CommentCreateResponse.builder()
            .name(savedComment.getName())
            .commentId(savedComment.getId())
            .createdDate(savedComment.getCreatedDate())
            .build();

    }

    @Transactional(readOnly = true)
    public List<CommentResponse> fetchComment(
        final Long itemId,
        final Long lastCommentId,
        final int fetchSize
    ) {
        return commentQueryRepository.paginationNoOffsetComment(lastCommentId, itemId, fetchSize);
    }



}