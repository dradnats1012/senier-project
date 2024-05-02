package koreatechbus.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import koreatechbus.dto.comment.RequestCommentDTO;
import koreatechbus.dto.comment.ResponseCommentDTO;
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

    @GetMapping("/{postId}")
    public ResponseEntity<List<ResponseCommentDTO>> getCommentByPostId(
        @PathVariable Long postId) {
        List<ResponseCommentDTO> comments = commentService.getCommentByPostId(postId);
        return ResponseEntity.ok().body(comments);
    }
}
