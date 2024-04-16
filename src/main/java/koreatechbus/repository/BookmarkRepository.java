package koreatechbus.repository;

import java.awt.print.Book;

import koreatechbus.domain.Bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Bookmark save(Bookmark bookmark);

    void deleteByBookmarkId(Long bookmarkId);

    Bookmark findByBookmarkId(Long bookmarkId);
}
