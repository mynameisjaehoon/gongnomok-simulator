package site.gongnomok.core.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.gongnomok.common.comment.dto.request.CommentCreateServiceDto;
import site.gongnomok.common.comment.dto.request.CommentDeleteServiceDto;
import site.gongnomok.common.comment.dto.response.CommentCountResponse;
import site.gongnomok.common.comment.dto.response.CommentDto;
import site.gongnomok.common.exception.CommentException;
import site.gongnomok.common.exception.ExceptionCode;
import site.gongnomok.common.exception.IncludeBanWordException;
import site.gongnomok.common.exception.ItemException;
import site.gongnomok.common.global.util.SecurityUtil;
import site.gongnomok.core.banword.wordfilter.BanWordFilter;
import site.gongnomok.data.comment.domain.Comment;
import site.gongnomok.data.comment.domain.CommentCount;
import site.gongnomok.data.comment.domain.repository.CommentCountRedisRepository;
import site.gongnomok.data.comment.domain.repository.CommentJpaRepository;
import site.gongnomok.data.comment.domain.repository.CommentQueryRepository;
import site.gongnomok.data.item.domain.Item;
import site.gongnomok.data.item.domain.repository.ItemRepository;
import site.gongnomok.data.management.domain.ReportComment;
import site.gongnomok.data.management.domain.repository.ReportCommentJpaRepository;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final ItemRepository itemRepository;
    private final CommentQueryRepository commentQueryRepository;
    private final CommentJpaRepository commentJpaRepository;
    private final CommentCountRedisRepository commentCountRedisRepository;
    private final ReportCommentJpaRepository reportCommentJpaRepository;
    private final BanWordFilter banWordFilter;
    private final RedisTemplate<String, CommentCount> commentCountRedisTemplate;

    @Value("${comment.constant.max-count}")
    private int maxCount;

    @Value("${comment.constant.expire-seconds}")
    private int expireSeconds;

    public CommentDto createComment(
        final CommentCreateServiceDto createDto,
        final Long itemId,
        final String address
    ) {

        checkAndUpdateCommentCount(address);

        String encryptedPassword = SecurityUtil.encryptSha256(createDto.getPassword());
        boolean result = banWordFilter.checkContainBanWord(createDto.getContent());

        if (result) {
            // 금칙어가 포함되어 있는 경우 예외를 던진다.
            throw new IncludeBanWordException(ExceptionCode.INCLUDE_BAN_WORD);
        }

        Item findItem = itemRepository
            .findById(itemId)
            .orElseThrow(() -> new ItemException(ExceptionCode.NOT_FOUND_ITEM_ID));
        Comment newComment = Comment.of(createDto.getName(), encryptedPassword, createDto.getContent());

        newComment.changeItem(findItem);
        Comment savedComment = commentJpaRepository.save(newComment);

        return savedComment.toDto();

    }

    private void checkAndUpdateCommentCount(String address) {
        CommentCount commentCount = commentCountRedisRepository
            .findById(address)
            .orElse(CommentCount.init(address));

        if (commentCount.getCount() >= maxCount) {
            throw new CommentException(ExceptionCode.COMMENT_RATE_LIMIT);
        }

        commentCount.increaseCount();
        commentCountRedisRepository.save(commentCount);
    }

    @Transactional(readOnly = true)
    public CommentCountResponse countComment(final Long itemId) {
        return new CommentCountResponse(commentQueryRepository.commentCount(itemId));
    }

    @Transactional(readOnly = true)
    public List<CommentDto> fetchComment(
        final Long itemId,
        final Long lastCommentId,
        final int fetchSize
    ) {
        return commentQueryRepository.paginationNoOffsetComment(lastCommentId, itemId, fetchSize);
    }

    public void deleteComment(final CommentDeleteServiceDto deleteDto) {
        Comment findComment = commentJpaRepository
            .findById(deleteDto.getCommentId())
            .orElseThrow(() -> new CommentException(ExceptionCode.NOT_FOUND_COMMENT_ID));

        String encryptedPassword = SecurityUtil.encryptSha256(deleteDto.getPassword());
        if (!encryptedPassword.equals(findComment.getPassword())) {
            throw new CommentException(ExceptionCode.INVALID_PASSWORD);
        }

        commentJpaRepository.delete(findComment);
    }

    public void reportComment(final Long commentId) {
        Comment findComment = commentJpaRepository
            .findById(commentId)
            .orElseThrow(() -> new CommentException(ExceptionCode.NOT_FOUND_COMMENT_ID));

        reportCommentJpaRepository
            .findReportCommentByCommentId(commentId)
            .ifPresentOrElse((ReportComment::addCount), () -> {
                ReportComment newReport = ReportComment.from(findComment);
                reportCommentJpaRepository.save(newReport);
            });
    }
}
