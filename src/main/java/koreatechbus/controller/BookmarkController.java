package koreatechbus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import koreatechbus.domain.Bookmark;
import koreatechbus.dto.bookmark.BookMarkDTO;
import koreatechbus.service.BookmarkService;
import koreatechbus.swaggerapi.BookmarkApi;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController implements BookmarkApi {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService){
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/register")
    public ResponseEntity<Bookmark> registerBookmark(
        @RequestBody BookMarkDTO bookMarkDTO) {
        return ResponseEntity.ok().body(bookmarkService.registerBookmark(bookMarkDTO));
    }

    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<Void> deleteBookmark(
        @PathVariable("bookmarkId") Long bookmarkId
    ){
        bookmarkService.deleteBookmark(bookmarkId);
        return ResponseEntity.ok().build();
    }
}
