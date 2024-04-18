package koreatechbus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import koreatechbus.domain.Bookmark;
import koreatechbus.domain.Bus;
import koreatechbus.domain.User;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Bookmark save(Bookmark bookmark);

    void deleteByBookmarkId(Long bookmarkId);

    Bookmark findByBookmarkId(Long bookmarkId);

    Boolean existsByUserAndBus(User user, Bus bus);

    List<Bookmark> getBookmarksByUser(User user);
}
