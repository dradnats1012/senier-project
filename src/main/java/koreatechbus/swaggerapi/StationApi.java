package koreatechbus.swaggerapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import koreatechbus.domain.Station;
import koreatechbus.dto.station.RegisterStationDTO;

@Tag(name = "정류장 api", description = "정류장 api")
public interface    StationApi {
    @Operation(summary = "정류장 추가")
    @PostMapping("/register")
    ResponseEntity<Station> registerStation(
        @RequestBody RegisterStationDTO registerStationDTO
    );
}
