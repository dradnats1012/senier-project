package koreatechbus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import koreatechbus.domain.Comment;
import koreatechbus.domain.Post;
import koreatechbus.domain.User;
import koreatechbus.dto.comment.RequestCommentDTO;
import koreatechbus.dto.comment.ResponseCommentDTO;
import koreatechbus.repository.CommentRepository;
import koreatechbus.repository.PostRepository;
import koreatechbus.repository.UserRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository,
        PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public void newComment(RequestCommentDTO requestCommentDTO) {
        User user = userRepository.findByUserId(requestCommentDTO.userId());
        Post post = postRepository.findByPostId(requestCommentDTO.postId());

        Comment comment = Comment.builder()
            .content(requestCommentDTO.content())
            .postTime(requestCommentDTO.postTime())
            .anonymous(requestCommentDTO.anonymous())
            .post(post)
            .user(user)
            .build();

        commentRepository.save(comment);
    }

    public List<ResponseCommentDTO> getCommentsByPostId(Long postId) {
        Post post = postRepository.findByPostId(postId);
        List<Comment> comments = commentRepository.findAllByPostOrderByCommentIdDesc(post);
        List<ResponseCommentDTO> commentDTOS = new ArrayList<>();

        for (Comment comment : comments) {
            User user = post.getUser();

            commentDTOS.add(ResponseCommentDTO.of(comment.getCommentId(), comment.getContent(), comment.getPostTime(),
                user.getUserId(), user.getName(), comment.getAnonymous()));
        }

        return commentDTOS;
    }

    public Integer countByPostId(Long postId) {
        Post post = postRepository.findByPostId(postId);
        return commentRepository.countAllByPost(post);
    }
}
