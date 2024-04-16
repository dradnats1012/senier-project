package koreatechbus.service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import koreatechbus.domain.Post;
import koreatechbus.domain.User;
import koreatechbus.dto.post.RequestPostDTO;
import koreatechbus.dto.post.ResponsePostDTO;
import koreatechbus.repository.PostRepository;
import koreatechbus.repository.UserRepository;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void newPost(RequestPostDTO requestPostDTO) {
        User user = userRepository.findByUserId(requestPostDTO.userId());

        Post post = Post.builder()
            .title(requestPostDTO.title())
            .content(requestPostDTO.content())
            .postTime(requestPostDTO.postTime())
            .postType(requestPostDTO.postType())
            .user(user)
            .build();

        postRepository.save(post);
    }

    public List<ResponsePostDTO> getAllPost() {
        List<Post> allPost = postRepository.findAllByOrderByPostIdDesc();
        List<ResponsePostDTO> postDTOS = new ArrayList<>();

        for (Post post : allPost) {
            User user = post.getUser();
            postDTOS.add(ResponsePostDTO.of(post.getTitle(), post.getContent(), post.getPostTime(), user.getName()));
        }

        return postDTOS;
    }

    public ResponsePostDTO getPostById(Long postId) {
        Post post = postRepository.findByPostId(postId);
        User user = post.getUser();

        return ResponsePostDTO.of(post.getTitle(), post.getContent(), post.getPostTime(), user.getName());
    }

    public List<ResponsePostDTO> getPostByType(Long postType) {
        List<Post> posts = postRepository.findByPostTypeOrderByPostIdDesc(postType);
        List<ResponsePostDTO> postDTOS = new ArrayList<>();

        for (Post post : posts) {
            User user = post.getUser();
            postDTOS.add(ResponsePostDTO.of(post.getTitle(), post.getContent(), post.getPostTime(), user.getName()));
        }

        return postDTOS;
    }

    @Transactional
    public void deletePost(Long postId, Long userId) throws AccessDeniedException {
        Post post = postRepository.findByPostId(postId);

        if (!userId.equals(post.getUser().getUserId())) {
            throw new AccessDeniedException("글을 지울 권한이 없습니다!");
        }
        postRepository.deleteByPostId(postId);
    }
}

