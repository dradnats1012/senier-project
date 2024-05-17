package koreatechbus.dto.comment;

public record ResponseCommentDTO(
    Long commentId,
    String content,
    String postTime,
    Long userId,
    String userName,
    Boolean anonymous,
    String displayName
) {
    public static ResponseCommentDTO of(Long commentId, String content, String postTime, Long userId,
        String userName, Boolean anonymous, String displayName) {
        return new ResponseCommentDTO(commentId, content, postTime, userId, userName, anonymous, displayName);
    }
}
