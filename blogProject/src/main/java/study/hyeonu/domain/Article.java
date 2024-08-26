package study.hyeonu.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="author",nullable = false)
    private String author;

    @Builder // 빌더패턴
    public Article(String title, String content, String author){
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }

    // 댓글
    // mappedBy 는 자식 엔티티가 부모 엔티티와의 관계를 나타냄
    // cascade는 부모 엔티티 변경 시 자식 엔티티에 전파하기 위한 방법 중 삭제에 관한 설정값
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
}
