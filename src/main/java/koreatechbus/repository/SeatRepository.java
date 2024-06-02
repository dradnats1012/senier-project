package koreatechbus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import koreatechbus.domain.Bus;
import koreatechbus.domain.Seat;
import koreatechbus.domain.User;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    boolean existsByBusAndSeatNum(Bus bus, Long seatNum);

    boolean existsByUser(User user);

    Seat findByBusAndSeatNum(Bus bus, Long seatNum);

    List<Seat> findAllByBus(Bus bus);

    Seat findBySeatId(Long seatId);

    Seat findByUser(User user);
}
