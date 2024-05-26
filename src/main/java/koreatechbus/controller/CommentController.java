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
import koreatechbus.swaggerapi.CommentApi;

@RestController
@RequestMapping("/comment")
public class CommentController implements CommentApi {

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
    public ResponseEntity<List<ResponseCommentDTO>> getCommentsByPostId(
        @PathVariable Long postId
    ) {
        List<ResponseCommentDTO> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok().body(comments);
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<Integer> countByPostId(
        @PathVariable Long postId
    ) {
        return ResponseEntity.ok().body(commentService.countByPostId(postId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<String> getUserName(
        @PathVariable Long userId
    ){
        String name = commentService.getUserName(userId);
        return ResponseEntity.ok().body(name);
    }
}
