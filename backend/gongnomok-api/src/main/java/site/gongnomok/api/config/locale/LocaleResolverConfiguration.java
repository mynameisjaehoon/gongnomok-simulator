package site.gongnomok.api.config.locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleResolverConfiguration {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver lr = new SessionLocaleResolver();
        lr.setDefaultLocale(Locale.KOREA);
        return lr;
    }
}
