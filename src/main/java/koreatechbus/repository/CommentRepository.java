package koreatechbus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import koreatechbus.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
