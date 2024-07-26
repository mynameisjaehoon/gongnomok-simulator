package site.gongnomok.api.comment;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.gongnomok.common.comment.dto.request.CommentCreateDto;
import site.gongnomok.common.comment.dto.request.CommentDeleteDto;
import site.gongnomok.common.comment.dto.request.CommentReportDto;
import site.gongnomok.common.comment.dto.response.CommentCountResponse;
import site.gongnomok.common.comment.dto.response.CommentDto;
import site.gongnomok.core.comment.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/item/{itemId}/comment")
    public ResponseEntity<CommentDto> createComment(
        final HttpServletRequest request,
        @PathVariable(name = "itemId") final Long itemId,
        @RequestBody final CommentCreateDto requestDto
    ) {
        String address = request.getHeader("X-FORWARDED-FOR") != null ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
        CommentDto createdComment = commentService.createComment(requestDto.toServiceDto(), itemId, address);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdComment);
    }

    @GetMapping("/item/{itemId}/comment/count")
    public ResponseEntity<CommentCountResponse> countComment(
        @PathVariable("itemId") Long itemId
    ) {
        return ResponseEntity.ok(commentService.countComment(itemId));
    }

    @GetMapping("/item/{itemId}/comment")
    public ResponseEntity<List<CommentDto>> comment(
        @PathVariable(name = "itemId") Long itemId,
        @RequestParam("lastId") Long lastId,
        @RequestParam("size") int size
    ) {
        List<CommentDto> comments = commentService.fetchComment(itemId, lastId == -1 ? null : lastId, size);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/item/comment/delete")
    public ResponseEntity<Void> deleteComment(
        @RequestBody CommentDeleteDto deleteDto
    ) {

        commentService.deleteComment(deleteDto.toServiceDto());
        return ResponseEntity.ok().build();
    }

    /**
     * 댓글신고
     */
    @PostMapping("/item/comment/report")
    public ResponseEntity<Void> reportComment(
        @RequestBody CommentReportDto reportDto
    ) {
        commentService.reportComment(reportDto.getCommentId());
        return ResponseEntity.noContent().build();
    }

}
