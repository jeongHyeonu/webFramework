package study.hyeonu.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt") // 자바 클래스에 application.yml 의 프로퍼티값을 가져와 사용
public class JwtProperties {
    private String issuer;
    private String secretKey;
}
