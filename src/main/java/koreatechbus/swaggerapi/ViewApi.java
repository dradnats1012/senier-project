package koreatechbus.swaggerapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import koreatechbus.dto.etc.MainDTO;

@Tag(name = "화면 api", description = "화면 api")
public interface ViewApi {

    @Operation(summary = "메인화면")
    @GetMapping("/main")
    ResponseEntity<MainDTO> getMain(
        @RequestParam
        String token
    );
}
