package koreatechbus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import koreatechbus.domain.Comment;
import koreatechbus.dto.comment.RequestCommentDTO;
import koreatechbus.repository.CommentRepository;
import koreatechbus.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Void> newComment(
        RequestCommentDTO requestCommentDTO
    ) {
        commentService.newComment(requestCommentDTO);
        return ResponseEntity.ok().build();
    }
}
