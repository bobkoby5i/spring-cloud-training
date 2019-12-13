package pl.training.cloud.trips.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class FeignAuthorizationTokenInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "bearer ";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(AUTHORIZATION_HEADER, TOKEN_PREFIX + getToken());
    }

    private String getToken() {
        return ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getDetails())
                .getTokenValue();
    }

}
