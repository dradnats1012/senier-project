package koreatechbus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import koreatechbus.domain.User;
import koreatechbus.dto.user.LoginDTO;
import koreatechbus.dto.user.NotificationPermitRequest;
import koreatechbus.dto.user.RegisterDTO;
import koreatechbus.service.UserService;
import koreatechbus.swaggerapi.UserApi;

@RestController
@RequestMapping("/user")
public class UserController implements UserApi {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registers")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDTO registerDTO) throws IllegalAccessException {
        return ResponseEntity.ok().body(userService.registerUser(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok().body(userService.loginUser(loginDTO));
    }

    @GetMapping("/{token}")
    public ResponseEntity<Long> getUserId(
        @PathVariable String token
    ){
        return ResponseEntity.ok().body(userService.getUserId(token));
    }

    @PostMapping("/notification")
    public ResponseEntity<Void> permitNotification(
        Long userId,
        @RequestBody NotificationPermitRequest request
    ) {
        userService.permitNotification(userId, request.deviceToken());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/notification")
    public ResponseEntity<Void> rejectNotification(
        Long userId
    ) {
        userService.rejectNotification(userId);
        return ResponseEntity.ok().build();
    }
}
