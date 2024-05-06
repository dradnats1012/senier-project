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

import koreatechbus.domain.StationBookmark;
import koreatechbus.dto.stationbookmark.StationBookmarkDTO;
import koreatechbus.service.StationBookmarkService;
import koreatechbus.swaggerapi.StationBookmarkApi;

@RestController
@RequestMapping("/stationbookmark")
public class StationBookmarkController implements StationBookmarkApi {

    private final StationBookmarkService stationBookmarkService;

    public StationBookmarkController(StationBookmarkService stationBookmarkService) {
        this.stationBookmarkService = stationBookmarkService;
    }

    @PostMapping("/register")
    public ResponseEntity<StationBookmark> registerBookmark(
        @RequestBody StationBookmarkDTO stationBookmarkDTO) throws IllegalAccessException {
        return ResponseEntity.ok().body(stationBookmarkService.registerBookmark(stationBookmarkDTO));
    }

    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<Void> deleteBookmark(
        @PathVariable("bookmarkId") Long bookmarkId
    ) {
        stationBookmarkService.deleteBookmark(bookmarkId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<StationBookmark>> getBookmarks(
        @PathVariable Long userId
    ) {
        List<StationBookmark> stationBookmarks = stationBookmarkService.getBookmarks(userId);
        return ResponseEntity.ok().body(stationBookmarks);
    }
}
