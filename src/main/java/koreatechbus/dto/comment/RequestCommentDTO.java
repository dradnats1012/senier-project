package koreatechbus.dto.comment;

public record RequestCommentDTO(
    String content,
    String postTime,
    Long postId,
    Long userId,
    Boolean anonymous
) {
}
