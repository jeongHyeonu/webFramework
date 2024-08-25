package study.hyeonu.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import study.hyeonu.domain.User;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiedAt){
        Date now = new Date();
        return makeToken(new Date(now.getTime()+expiedAt.toMillis()),user);
    };

    private String makeToken(Date expiry, User user){
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer()) // iss : (application.yml 에서 설정한 값)
                .setIssuedAt(now)           // iat : 현재시간
                .setExpiration(expiry)      // exp : expiry
                .setSubject(user.getEmail()) // sub : 유저이메일
                .claim("id",user.getId()) // 클레임 id : 유저ID
                // 서명 : 비밀값과 함께 해시값을 HS256 방식으로 암호화
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public boolean validToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) // 비밁밧으로 복호화
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e){ // 복호화 에러가 나면 유효하지 않은 토큰임
            return false;
        }
    }

    // 토큰 기반으로 인증 정보 가져오기
    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(claims.getSubject(),"",authorities),
                token,
                authorities);
    }

    // 토큰 기반으로 유저 ID 가져오기
    public Long getUserId(String token){
        Claims claims = getClaims(token);
        return claims.get("id",Long.class);
    }

    private Claims getClaims(String token){
        return Jwts.parser() // 클레임 조회
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

}
