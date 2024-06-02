package koreatechbus.swaggerapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import koreatechbus.domain.Seat;
import koreatechbus.dto.seat.ReservationSeatDTO;

@Tag(name = "좌석 api", description = "좌석 예약 관련 api")
public interface SeatApi {

    @Operation(summary = "노선 좌석 불러오기")
    @GetMapping("/{busId}")
    ResponseEntity<List<Seat>> getSeatsByBusId(
        @PathVariable Long busId
    );

    @Operation(summary = "좌석 예약하기")
    @PutMapping("/reservate")
    ResponseEntity<Void> reservationSeat(
        @RequestBody ReservationSeatDTO reservationSeatDTO) throws IllegalAccessException;

    @Operation(summary = "좌석ID로 좌석 취소하기")
    @DeleteMapping("/{seatId}")
    ResponseEntity<Void> cancelSeatBySeatId(
        @PathVariable Long seatId
    );

    @Operation(summary = "사용자로 좌석 취소하기")
    @DeleteMapping("/user/{userId}")
    ResponseEntity<Void> cancelSeatByUserId(
        @PathVariable Long userId
    );
}
