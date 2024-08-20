package study.hyeonu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.hyeonu.domain.Article;

public interface BlogRepository extends JpaRepository<Article,Long> {

}
