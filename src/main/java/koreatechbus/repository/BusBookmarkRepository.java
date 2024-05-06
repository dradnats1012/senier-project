package koreatechbus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import koreatechbus.domain.BusBookmark;
import koreatechbus.domain.Bus;
import koreatechbus.domain.User;

public interface BusBookmarkRepository extends JpaRepository<BusBookmark, Long> {
    BusBookmark save(BusBookmark busBookmark);

    void deleteByBookmarkId(Long bookmarkId);

    BusBookmark findByBookmarkId(Long bookmarkId);

    Boolean existsByUserAndBus(User user, Bus bus);

    List<BusBookmark> getBusBookmarksByUser(User user);
}
