package koreatechbus.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import koreatechbus.domain.BusBookmark;
import koreatechbus.dto.busbookmark.BusBookMarkDTO;
import koreatechbus.service.BusBookmarkService;
import koreatechbus.swaggerapi.BusBookmarkApi;

@RestController
@RequestMapping("/busbookmark")
public class BusBookmarkController implements BusBookmarkApi {

    private final BusBookmarkService busBookmarkService;

    public BusBookmarkController(BusBookmarkService busBookmarkService) {
        this.busBookmarkService = busBookmarkService;
    }

    @PostMapping("/register")
    public ResponseEntity<BusBookmark> registerBookmark(
        @RequestBody BusBookMarkDTO busBookMarkDTO) throws IllegalAccessException {
        return ResponseEntity.ok().body(busBookmarkService.registerBookmark(busBookMarkDTO));
    }

    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<Void> deleteBookmark(
        @PathVariable("bookmarkId") Long bookmarkId
    ) {
        busBookmarkService.deleteBookmark(bookmarkId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<BusBookmark>> getBookmarks(
        @PathVariable Long userId
    ){
        List<BusBookmark> busBookmarks = busBookmarkService.getBookmarks(userId);
        return ResponseEntity.ok().body(busBookmarks);
    }
}
