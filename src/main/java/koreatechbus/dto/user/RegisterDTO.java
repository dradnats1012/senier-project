package koreatechbus.dto.user;

public record RegisterDTO (
    String schoolId,
    String name,
    String password,
    String email
) {

}
