package koreatechbus.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import koreatechbus.domain.Bus;
import koreatechbus.dto.bus.BusDTO;
import koreatechbus.dto.bus.BusPositionDTO;
import koreatechbus.dto.bus.GetBusDTO;
import koreatechbus.dto.bus.NewBusDTO;
import koreatechbus.service.BusService;
import koreatechbus.swaggerapi.BusApi;

@RestController
@RequestMapping("/bus")
public class BusController implements BusApi {
    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping()
    public ResponseEntity<List<BusDTO>> getAllBus() {
        return ResponseEntity.ok().body(busService.getAllBus());
    }

    @PostMapping
    public ResponseEntity<Bus> putBus(@RequestBody NewBusDTO newBusDTO) {
        return ResponseEntity.ok().body(busService.putBus(newBusDTO));
    }

    @GetMapping("/{busId}")
    public ResponseEntity<GetBusDTO> getBusById(
        @PathVariable("busId") Long busId
    ) {
        return ResponseEntity.ok().body(busService.getBusById(busId));
    }

    @PatchMapping("/{busId}")
    public ResponseEntity<Bus> updatePosition(
        @PathVariable Long busId,
        @RequestBody BusPositionDTO busPositionDTO
    ) {
        Bus bus = busService.updatePosition(busId, busPositionDTO);
        return ResponseEntity.ok().body(bus);
    }

    @GetMapping("/position/{busId}")
    public ResponseEntity<BusPositionDTO> getPosition(
        @PathVariable Long busId
    ) {
        return ResponseEntity.ok().body(busService.getPosition(busId));
    }

    @PatchMapping("/{busId}/run")
    public ResponseEntity<Void> updateIsRun(
        @PathVariable Long busId,
        @RequestParam Boolean isRun
    ){
        busService.changeIsRun(busId, isRun);
        return ResponseEntity.ok().build();
    }
}
