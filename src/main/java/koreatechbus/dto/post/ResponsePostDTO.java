package koreatechbus.dto.post;

public record ResponsePostDTO(
    Long postId,
    String title,
    String content,
    String postTime,
    String userName,
    Long postType,
    Boolean anonymous
) {
    public static ResponsePostDTO of(Long postId, String title, String content, String postTime, String userName,
        Long postType, Boolean anonymous) {
        return new ResponsePostDTO(postId, title, content, postTime, userName, postType, anonymous);
    }
}
