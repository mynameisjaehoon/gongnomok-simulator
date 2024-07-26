package site.gongnomok.data.configure;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import site.gongnomok.data.comment.domain.CommentCount;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, CommentCount> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, CommentCount> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(CommentCount.class));

        return template;
    }
}
