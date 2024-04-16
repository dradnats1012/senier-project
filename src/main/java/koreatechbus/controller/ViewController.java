package koreatechbus.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import koreatechbus.dto.etc.MainDTO;
import koreatechbus.service.UserService;
import koreatechbus.swaggerapi.ViewApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "화면 api", description = "화면 api")
@RestController
public class ViewController implements ViewApi {
    private final UserService userService;

    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/main")
    public ResponseEntity<MainDTO> getMain(@RequestParam String token) {
        return ResponseEntity.ok().body(userService.getMainPage(token));
    }
}
