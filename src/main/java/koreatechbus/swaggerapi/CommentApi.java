package koreatechbus.swaggerapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import koreatechbus.dto.comment.RequestCommentDTO;
import koreatechbus.dto.comment.ResponseCommentDTO;

@Tag(name = "댓글 api", description = "댓글 api")
public interface CommentApi {

    @Operation(summary = "댓글쓰기")
    @PostMapping
    ResponseEntity<Void> newComment(
        @RequestBody RequestCommentDTO requestCommentDTO
    );

    @Operation(summary = "댓글 불러오기")
    @GetMapping("/{postId}")
    ResponseEntity<List<ResponseCommentDTO>> getCommentByPostId(
        @PathVariable Long postId
    );
}
