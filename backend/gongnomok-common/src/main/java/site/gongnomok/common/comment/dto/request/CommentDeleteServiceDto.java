package site.gongnomok.common.comment.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CommentDeleteServiceDto {

    private Long commentId;
    private String password;

}
