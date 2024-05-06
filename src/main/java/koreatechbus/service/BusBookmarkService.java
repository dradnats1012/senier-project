package koreatechbus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import koreatechbus.domain.BusBookmark;
import koreatechbus.domain.Bus;
import koreatechbus.domain.User;
import koreatechbus.dto.busbookmark.BusBookMarkDTO;
import koreatechbus.repository.BusBookmarkRepository;
import koreatechbus.repository.BusRepository;
import koreatechbus.repository.UserRepository;

@Service
public class BusBookmarkService {
    private final BusBookmarkRepository busBookmarkRepository;
    private final UserRepository userRepository;
    private final BusRepository busRepository;

    public BusBookmarkService(BusBookmarkRepository busBookmarkRepository, UserRepository userRepository,
        BusRepository busRepository) {
        this.busBookmarkRepository = busBookmarkRepository;
        this.userRepository = userRepository;
        this.busRepository = busRepository;
    }

    public BusBookmark registerBookmark(BusBookMarkDTO busBookMarkDTO) throws IllegalAccessException {
        User user = userRepository.findByUserId(busBookMarkDTO.userId());
        Bus bus = busRepository.findByBusId(busBookMarkDTO.busId());
        if (busBookmarkRepository.existsByUserAndBus(user, bus)) {
            throw new IllegalAccessException("이미 관심노선으로 등록되어 있습니다!");
        }

        bus.plusBookmarkNum();
        busRepository.save(bus);

        BusBookmark busBookmark = new BusBookmark(user, bus);
        return busBookmarkRepository.save(busBookmark);
    }

    @Transactional
    public void deleteBookmark(Long bookmarkId) {
        BusBookmark busBookmark = busBookmarkRepository.findByBookmarkId(bookmarkId);
        Bus bus = busBookmark.getBus();
        busBookmarkRepository.deleteByBookmarkId(bookmarkId);

        bus.minusBookmarkNum();
        busRepository.save(bus);
    }

    public List<BusBookmark> getBookmarks(Long userId){
        User user = userRepository.findByUserId(userId);
        return busBookmarkRepository.getBusBookmarksByUser(user);
    }
}
