package koreatechbus.dto.post;

public record RequestPostDTO(
    String title,
    String content,
    String postTime,
    Long postType,
    Long userId
) {
}
