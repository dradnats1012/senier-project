package koreatechbus.repository;

import java.awt.print.Book;

import koreatechbus.domain.Bookmark;
import koreatechbus.domain.Bus;
import koreatechbus.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Bookmark save(Bookmark bookmark);

    void deleteByBookmarkId(Long bookmarkId);

    Bookmark findByBookmarkId(Long bookmarkId);

    Boolean existsByUserAndBus(User user, Bus bus);
}
