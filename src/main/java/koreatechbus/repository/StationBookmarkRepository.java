package koreatechbus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import koreatechbus.domain.Station;
import koreatechbus.domain.StationBookmark;
import koreatechbus.domain.User;

public interface StationBookmarkRepository extends JpaRepository<StationBookmark, Long> {

    void deleteByBookmarkId(Long bookmarkId);

    StationBookmark findByBookmarkId(Long bookmarkId);

    Boolean existsByUserAndStation(User user, Station station);

    List<StationBookmark> getStationBookmarksByUser(User user);
}
