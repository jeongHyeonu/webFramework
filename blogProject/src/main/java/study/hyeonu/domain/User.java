package study.hyeonu.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false)
    private Long id;

    @Column(name="email",nullable=false,unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Builder
    public User(String email,String password,String auth,String nickname){
        this.email=email;
        this.password=password;
        this.nickname=nickname;
    }

    public User update(String nickname){
        this.nickname = nickname;
        return this;
    }

    @Override // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override // 패스워드 반환
    public String getPassword() {
        return password;
    }

    @Override // 사용자 id (고유값) 반환
    public String getUsername() {
        return email;
    }

    @Override // 계정 만료 여부
    public boolean isAccountNonExpired() {
        return true; // true = 만료되지 않음
    }

    @Override // 계정 잠금 여부
    public boolean isAccountNonLocked() {
        return true; // true = 잠금되지 않음
    }

    @Override // 패스워드 만료 여부
    public boolean isCredentialsNonExpired() {
        return true; // true = 만료되지 않음
    }

    @Override // 계정 사용 가능 여부
    public boolean isEnabled() {
        return true;
    }
}
