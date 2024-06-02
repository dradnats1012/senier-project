package koreatechbus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import koreatechbus.domain.Bus;
import koreatechbus.domain.Seat;
import koreatechbus.domain.User;
import koreatechbus.dto.seat.ReservationSeatDTO;
import koreatechbus.repository.BusRepository;
import koreatechbus.repository.SeatRepository;
import koreatechbus.repository.UserRepository;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final BusRepository busRepository;
    private final UserRepository userRepository;

    public SeatService(SeatRepository seatRepository, BusRepository busRepository, UserRepository userRepository) {
        this.seatRepository = seatRepository;
        this.busRepository = busRepository;
        this.userRepository = userRepository;
    }

    public List<Seat> getSeatsByBus(Long busId){
        Bus bus = busRepository.findByBusId(busId);

        return seatRepository.findAllByBus(bus);
    }

    public void reservationSeat(ReservationSeatDTO reservationSeatDTO) throws IllegalAccessException {
        checkSeatExist(reservationSeatDTO);
        isUsing(reservationSeatDTO.userId());
        isUsedSeat(reservationSeatDTO);

        Bus bus = busRepository.findByBusId(reservationSeatDTO.busId());
        Seat seat = seatRepository.findByBusAndSeatNum(bus, reservationSeatDTO.seatNum());
        User user = userRepository.findByUserId(reservationSeatDTO.userId());

        seat.chooseSeat(user);
        seatRepository.save(seat);
    }

    private void checkSeatExist(ReservationSeatDTO dto) throws IllegalAccessException {
        Bus bus = busRepository.findByBusId(dto.busId());

        if (!seatRepository.existsByBusAndSeatNum(bus, dto.seatNum())) {
            throw new IllegalAccessException("존재하지 않는 좌석입니다!");
        }
    }

    private void isUsing(Long userId) {
        User user = userRepository.findByUserId(userId);

        if (seatRepository.existsByUser(user)) {
            throw new IllegalArgumentException("이미 좌석을 예약한 사용자입니다!");
        }
    }

    private void isUsedSeat(ReservationSeatDTO dto) {
        Bus bus = busRepository.findByBusId(dto.busId());

        Seat seat = seatRepository.findByBusAndSeatNum(bus, dto.seatNum());

        if(seat.getIsUsed().equals(true)){
            throw new IllegalStateException("이미 사용중인 좌석입니다!");
        }
    }

    public void cancelSeatBySeatId(Long seatId){
        Seat seat = seatRepository.findBySeatId(seatId);

        if(seat.getIsUsed().equals(false)){
            throw new IllegalArgumentException("해당 좌석은 사용중이 아닙니다.");
        }

        seat.cancelSeat();
        seatRepository.save(seat);
    }

    public void cancelByUser(Long userId){
        User user = userRepository.findByUserId(userId);
        Seat seat = seatRepository.findByUser(user);

        seat.cancelSeat();
        seatRepository.save(seat);
    }
}
