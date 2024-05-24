package koreatechbus.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import koreatechbus.dto.post.RequestPostDTO;
import koreatechbus.dto.post.ResponsePostDTO;
import koreatechbus.service.PostService;
import koreatechbus.swaggerapi.PostApi;

@RestController
@RequestMapping("/post")
public class PostController implements PostApi {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Void> newPost(
        @RequestBody RequestPostDTO requestPostDTO
    ) {
        postService.newPost(requestPostDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ResponsePostDTO>> getAllPost() {
        return ResponseEntity.ok().body(postService.getAllPost());
    }

    @GetMapping("/id/{postId}")
    public ResponseEntity<ResponsePostDTO> getPostById(
        @PathVariable Long postId
    ) {
        ResponsePostDTO responsePostDTO = postService.getPostById(postId);
        return ResponseEntity.ok().body(responsePostDTO);
    }

    @DeleteMapping("/id/{postId}")
    public ResponseEntity<Void> deletePost(
        @PathVariable Long postId,
        Long userId
    ) throws AccessDeniedException {

        postService.deletePost(postId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/type/{postType}")
    public ResponseEntity<List<ResponsePostDTO>> getPostByType(
        @PathVariable Long postType
    ) {
        List<ResponsePostDTO> postTypeList = postService.getPostByType(postType);

        return ResponseEntity.ok().body(postTypeList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponsePostDTO>> getMyPost(
        @PathVariable Long userId
    ) {
        return ResponseEntity.ok().body(postService.getMyPost(userId));
    }

    @GetMapping("/announcement")
    public ResponseEntity<Long> getAnnouncementNum()
    {
        return ResponseEntity.ok().body(postService.getAnnouncementNum());
    }
}
