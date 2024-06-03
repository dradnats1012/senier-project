package koreatechbus.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import koreatechbus.domain.Seat;
import koreatechbus.dto.seat.ReservationSeatDTO;
import koreatechbus.service.SeatService;
import koreatechbus.swaggerapi.SeatApi;

@Controller
@RequestMapping("seats")
public class SeatController implements SeatApi {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{busId}")
    public ResponseEntity<List<Seat>> getSeatsByBusId(
        @PathVariable Long busId
    ) {
        List<Seat> seats = seatService.getSeatsByBus(busId);
        return ResponseEntity.ok().body(seats);
    }

    @PutMapping("/reservate")
    public ResponseEntity<Void> reservationSeat(
        @RequestBody ReservationSeatDTO reservationSeatDTO) throws IllegalAccessException {

        seatService.reservationSeat(reservationSeatDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{seatId}")
    public ResponseEntity<Void> cancelSeatBySeatId(
        @PathVariable Long seatId
    ) {
        seatService.cancelSeatBySeatId(seatId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> cancelSeatByUserId(
        @PathVariable Long userId
    ) {
        seatService.cancelByUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Scheduled(cron = "0 1 0 * * *")
    public void endExpiredSeat() {
        seatService.resetSeat();
    }
}
