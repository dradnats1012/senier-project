package koreatechbus.swaggerapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import koreatechbus.domain.Bus;
import koreatechbus.dto.bus.BusDTO;
import koreatechbus.dto.bus.BusPositionDTO;
import koreatechbus.dto.bus.GetBusDTO;
import koreatechbus.dto.bus.NewBusDTO;

@Tag(name = "버스(노선) api", description = "버스(노선) api")
public interface BusApi {
    @Operation(summary = "모든 버스(노선) 불러오기")
    @GetMapping()
    ResponseEntity<List<BusDTO>> getAllBus();

    @Operation(summary = "새로운 버스(노선) 추가")
    @PostMapping
    ResponseEntity<Bus> putBus(
        @RequestBody NewBusDTO newBusDTO
    );

    @Operation(summary = "특정 버스(노선) 불러오기")
    @GetMapping("/{busId}")
    ResponseEntity<GetBusDTO> getBusById(
        @PathVariable("busId") Long busId
    );

    @Operation(summary = "특정 버스 위도, 경도 변경")
    @PatchMapping("/{busId}")
    ResponseEntity<Bus> updatePosition(
        @PathVariable("busId") Long busId,
        @RequestBody BusPositionDTO dto
    );

    @Operation(summary = "특정 버스 위도, 경도 가져오기")
    @GetMapping("/position/{busId}")
    ResponseEntity<BusPositionDTO> getPosition(
        @PathVariable("busId") Long busId
    );
}
