package study.hyeonu.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import study.hyeonu.config.jwt.TokenProvider;

import java.io.IOException;

// [ token filter ]
// 요청이 오면 헤더값 비교해서 토큰 있는지 확인
// 유효 토큰인 경우 시큐리티 콘텍스트 홀더에 인증정보 저장
// security context는 인증 객체가 저장되는 보관소 - 스레드 로컬에 저장되므로 코드 아무곳에서나 참조 가능

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        String token = getAccessToken(authorizationHeader);

        if (tokenProvider.validToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
