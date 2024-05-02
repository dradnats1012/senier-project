package koreatechbus.dto.comment;

public record ResponseCommentDTO(
    Long commentId,
    String content,
    String postTime,
    String userName,
    Boolean anonymous
) {
    public static ResponseCommentDTO of(Long commentId, String content, String postTime, String userName,
        Boolean anonymous) {
        return new ResponseCommentDTO(commentId, content, postTime, userName, anonymous);
    }
}
