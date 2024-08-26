package study.hyeonu.dto.Comments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.hyeonu.domain.Article;
import study.hyeonu.domain.Comment;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCommentRequest {
    private Long articleId;
    private String content;

    public Comment toEntity(String author, Article article){
        return Comment.builder()
                .article(article)
                .content(content)
                .author(author)
                .build();
    }
}
