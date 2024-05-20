package koreatechbus.dto.post;

public record RequestPostDTO(
    String title,
    String content,
    String postTime,
    Long postType,
    Long userId,
    String imageUrl,
    String attachmentUrl,
    Boolean anonymous
) {
}
