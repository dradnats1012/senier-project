package koreatechbus.dto.seat;

public record ReservationSeatDTO(
    Long busId,
    Long userId,
    Long seatNum
) {
}
