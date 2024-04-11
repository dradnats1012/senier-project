package koreatechbus.swaggerapi;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import koreatechbus.dto.post.RequestPostDTO;
import koreatechbus.dto.post.ResponsePostDTO;

@Tag(name = "게시판 api", description = "게시판 api")
public interface PostApi {

    @Operation(summary = "글쓰기")
    @PostMapping
    ResponseEntity<Void> newPost(
        @RequestBody RequestPostDTO requestPostDTO
    );

    @Operation(summary = "모든 글 불러오기")
    @GetMapping
    ResponseEntity<List<ResponsePostDTO>> getAllPost();

    @Operation(summary = "특정 글 불러오기")
    @GetMapping("/id/{postId}")
    ResponseEntity<ResponsePostDTO> getPostById(
        @PathVariable Long postId
    );

    @Operation(summary = "특정 글 삭제")
    @DeleteMapping("/id/{postId}")
    ResponseEntity<Void> deletePost(
        @PathVariable Long postId,
        Long userId
    )throws AccessDeniedException;

    @Operation(summary = "특정 글 유형 불러오기")
    @GetMapping("/type/{postType}")
    ResponseEntity<List<ResponsePostDTO>> getPostByType(
        @PathVariable Long postType
    );
}
