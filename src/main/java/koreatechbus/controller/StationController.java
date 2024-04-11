package koreatechbus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import koreatechbus.domain.Station;
import koreatechbus.dto.station.RegisterStationDTO;
import koreatechbus.service.StationService;
import koreatechbus.swaggerapi.StationApi;

@RestController
@RequestMapping("/station")
public class StationController implements StationApi {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Station> registerStation(
        @RequestBody RegisterStationDTO registerStationDTO
    ) {
        return ResponseEntity.ok().body(stationService.registerStation(registerStationDTO));
    }
}
