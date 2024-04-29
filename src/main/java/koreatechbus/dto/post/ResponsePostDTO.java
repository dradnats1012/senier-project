package koreatechbus.dto.post;

public record ResponsePostDTO(
    Long postId,
    String title,
    String content,
    String postTime,
    String userName,
    Long postType
) {
    public static ResponsePostDTO of(Long postId, String title, String content, String postTime, String userName, Long postType) {
        return new ResponsePostDTO(postId, title, content, postTime, userName, postType);
    }
}
