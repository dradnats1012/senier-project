package koreatechbus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import koreatechbus.domain.User;
import koreatechbus.dto.user.LoginDTO;
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

    /*@GetMapping("/information")
    public void getInformation(@RequestParam String token){
        System.out.println("들어옴");
        jwtProvider.getInformation(token);
    }*/

}
