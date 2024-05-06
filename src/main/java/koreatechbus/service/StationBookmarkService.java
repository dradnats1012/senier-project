package koreatechbus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import koreatechbus.domain.Station;
import koreatechbus.domain.StationBookmark;
import koreatechbus.domain.User;
import koreatechbus.dto.stationbookmark.StationBookmarkDTO;
import koreatechbus.repository.StationBookmarkRepository;
import koreatechbus.repository.StationRepository;
import koreatechbus.repository.UserRepository;

@Service
public class StationBookmarkService {
    private final StationBookmarkRepository stationBookmarkRepository;
    private final UserRepository userRepository;
    private final StationRepository stationRepository;

    public StationBookmarkService(StationBookmarkRepository stationBookmarkRepository, UserRepository userRepository,
        StationRepository stationRepository) {
        this.stationBookmarkRepository = stationBookmarkRepository;
        this.userRepository = userRepository;
        this.stationRepository = stationRepository;
    }

    public StationBookmark registerBookmark(StationBookmarkDTO stationBookmarkDTO) throws IllegalAccessException {
        User user = userRepository.findByUserId(stationBookmarkDTO.userId());
        Station station = stationRepository.findByStationId(stationBookmarkDTO.stationId());
        if (stationBookmarkRepository.existsByUserAndStation(user, station)) {
            throw new IllegalAccessException("이미 관심노선으로 등록되어 있습니다!");
        }

        /*station.plusBookmarkNum();    즐겨찾기 등록수 체크용
        busRepository.save(bus);*/

        StationBookmark stationBookmark = new StationBookmark(user, station);
        return stationBookmarkRepository.save(stationBookmark);
    }

    @Transactional
    public void deleteBookmark(Long bookmarkId) {
        stationBookmarkRepository.deleteByBookmarkId(bookmarkId);
    }

    public List<StationBookmark> getBookmarks(Long userId) {
        User user = userRepository.findByUserId(userId);
        return stationBookmarkRepository.getStationBookmarksByUser(user);
    }
}
