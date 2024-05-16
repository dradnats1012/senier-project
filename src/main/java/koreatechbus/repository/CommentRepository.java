package koreatechbus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import koreatechbus.domain.Comment;
import koreatechbus.domain.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostOrderByCommentIdDesc(Post post);

    Integer countAllByPost(Post post);
}
