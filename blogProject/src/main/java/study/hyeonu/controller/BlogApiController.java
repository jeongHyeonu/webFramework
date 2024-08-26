package study.hyeonu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.hyeonu.domain.Article;
import study.hyeonu.domain.Comment;
import study.hyeonu.dto.Articles.AddArticleRequest;
import study.hyeonu.dto.Articles.ArticleResponse;
import study.hyeonu.dto.Comments.AddCommentRequest;
import study.hyeonu.dto.Comments.AddCommentResponse;
import study.hyeonu.dto.Articles.UpdateArticleRequest;
import study.hyeonu.service.BlogService;

import java.util.List;


import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody @Valid AddArticleRequest request, Principal principal) {
        Article savedArticle = blogService.save(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article article = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest article){
        Article updatedArticle = blogService.update(id,article);
        return ResponseEntity.ok().body(updatedArticle);
    }

    @PostMapping("/api/comments")
    public ResponseEntity<AddCommentResponse> addComment(@RequestBody AddCommentRequest request, Principal principal){
        Comment savedComment = blogService.addComment(request,principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AddCommentResponse(savedComment));
    }
}
