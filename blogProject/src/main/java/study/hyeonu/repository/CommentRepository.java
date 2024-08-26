package study.hyeonu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.hyeonu.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
