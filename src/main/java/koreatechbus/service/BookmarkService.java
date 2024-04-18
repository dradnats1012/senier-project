package koreatechbus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import koreatechbus.domain.Bookmark;
import koreatechbus.domain.Bus;
import koreatechbus.domain.User;
import koreatechbus.dto.bookmark.BookMarkDTO;
import koreatechbus.repository.BookmarkRepository;
import koreatechbus.repository.BusRepository;
import koreatechbus.repository.UserRepository;

@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final BusRepository busRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository, UserRepository userRepository,
        BusRepository busRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.userRepository = userRepository;
        this.busRepository = busRepository;
    }

    public Bookmark registerBookmark(BookMarkDTO bookMarkDTO) throws IllegalAccessException {
        User user = userRepository.findByUserId(bookMarkDTO.userId());
        Bus bus = busRepository.findByBusId(bookMarkDTO.busId());
        if (bookmarkRepository.existsByUserAndBus(user, bus)) {
            throw new IllegalAccessException("이미 관심노선으로 등록되어 있습니다!");
        }

        bus.plusBookmarkNum();
        busRepository.save(bus);

        Bookmark bookmark = new Bookmark(user, bus);
        return bookmarkRepository.save(bookmark);
    }

    @Transactional
    public void deleteBookmark(Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findByBookmarkId(bookmarkId);
        Bus bus = bookmark.getBus();
        bookmarkRepository.deleteByBookmarkId(bookmarkId);

        bus.minusBookmarkNum();
        busRepository.save(bus);
    }

    public List<Bookmark> getBookmarks(Long userId){
        User user = userRepository.findByUserId(userId);
        return bookmarkRepository.getBookmarksByUser(user);
    }
}
