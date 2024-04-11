package koreatechbus.dto.post;

public record ResponsePostDTO(
    String title,
    String content,
    String postTime,
    String userName
) {
    public static ResponsePostDTO of(String title, String content, String postTime, String userName) {
        return new ResponsePostDTO(title, content, postTime, userName);
    }
}
