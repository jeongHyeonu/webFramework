package study.hyeonu.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import study.hyeonu.config.error.exception.ArticleNotFoundException;
import study.hyeonu.domain.Article;
import study.hyeonu.domain.Comment;
import study.hyeonu.dto.Articles.AddArticleRequest;
import study.hyeonu.dto.Comments.AddCommentRequest;
import study.hyeonu.dto.Articles.UpdateArticleRequest;
import study.hyeonu.repository.BlogRepository;
import study.hyeonu.repository.CommentRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public Article save(AddArticleRequest request, String userName) {
        return blogRepository.save(request.toEntity(userName));
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);
    }

    public void delete(long id) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        blogRepository.delete(article);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    // 게시글을 작성한 유저인지 확인
    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }

    public Comment addComment(AddCommentRequest request, String userName){
        Article article = blogRepository.findById(request.getArticleId())
                .orElseThrow(()->new IllegalArgumentException("not found : "+request.getArticleId()));

        return commentRepository.save(request.toEntity(userName,article));
    }

}