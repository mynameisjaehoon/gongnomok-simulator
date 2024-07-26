package site.gongnomok.data.comment.domain.repository;

import org.springframework.data.repository.CrudRepository;
import site.gongnomok.data.comment.domain.CommentCount;

public interface CommentCountRedisRepository extends CrudRepository<CommentCount, String> {
}
