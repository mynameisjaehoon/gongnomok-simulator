package site.gongnomok.data.comment.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "comment_count", timeToLive = 60L)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentCount {

    @Id
    private String address;
    private int count;

    public CommentCount(String address, int count) {
        this.address = address;
        this.count = count;
    }

    public int increaseCount() {
        count++;
        return count;
    }

    public static CommentCount init(String address) {
        return new CommentCount(address, 1);
    }

}
