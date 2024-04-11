package koreatechbus.repository;

import koreatechbus.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Bookmark save(Bookmark bookmark);
}
