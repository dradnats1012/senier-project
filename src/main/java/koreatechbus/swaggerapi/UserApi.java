package koreatechbus.swaggerapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import koreatechbus.domain.User;
import koreatechbus.dto.user.LoginDTO;
import koreatechbus.dto.user.RegisterDTO;

@Tag(name = "회원 api", description = "회원 api")
public interface UserApi {

    @Operation(summary = "회원가입")
    @PostMapping("/registers")
    ResponseEntity<User> registerUser(
        @RequestBody RegisterDTO registerDTO
    ) throws IllegalAccessException;

    @Operation(summary = "로그인")
    @PostMapping("/login")
    ResponseEntity<String> login(
        @RequestBody LoginDTO loginDTO
    );

    @Operation(summary = "userId 가져오기")
    @GetMapping("/{token}")
    ResponseEntity<Long> getUserId(
        @PathVariable String token
    );
}
