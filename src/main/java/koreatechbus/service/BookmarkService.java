package koreatechbus.service;

import org.springframework.stereotype.Service;

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

    public BookmarkService(BookmarkRepository bookmarkRepository, UserRepository userRepository, BusRepository busRepository){
        this.bookmarkRepository = bookmarkRepository;
        this.userRepository = userRepository;
        this.busRepository = busRepository;
    }

    public Bookmark registerBookmark(BookMarkDTO bookMarkDTO){
        User user = userRepository.findByUserId(bookMarkDTO.userId());
        Bus bus = busRepository.findByBusId(bookMarkDTO.busId());

        Bookmark bookmark = new Bookmark(user, bus);
        return bookmarkRepository.save(bookmark);
    }
}
