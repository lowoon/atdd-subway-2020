package wooteco.security.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import wooteco.security.core.LoginMemberArgumentResolver;
import wooteco.security.web.AuthenticationPrincipalArgumentResolver;

@Configuration
public class AuthenticationPrincipalConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List argumentResolvers) {
        argumentResolvers.addAll(
            Arrays.asList(createAuthenticationPrincipalArgumentResolver(), createLoginMemberArgumentResolver()));
    }

    @Bean
    public AuthenticationPrincipalArgumentResolver createAuthenticationPrincipalArgumentResolver() {
        return new AuthenticationPrincipalArgumentResolver();
    }

    @Bean
    public LoginMemberArgumentResolver createLoginMemberArgumentResolver() {
        return new LoginMemberArgumentResolver();
    }
}
