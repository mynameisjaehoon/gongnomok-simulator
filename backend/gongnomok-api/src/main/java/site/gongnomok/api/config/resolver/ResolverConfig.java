package site.gongnomok.api.config.resolver;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.gongnomok.api.admin.AdminLoginArgumentResolver;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ResolverConfig implements WebMvcConfigurer {

    private final AdminLoginArgumentResolver adminLoginArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(adminLoginArgumentResolver);
    }
}
