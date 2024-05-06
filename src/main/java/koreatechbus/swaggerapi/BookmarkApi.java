package koreatechbus.swaggerapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import koreatechbus.domain.BusBookmark;
import koreatechbus.dto.bookmark.BookMarkDTO;

@Tag(name = "북마크 api", description = "북마크 api")
public interface BookmarkApi {
    @Operation(summary = "관심노선 등록")
    @PostMapping("/register")
    ResponseEntity<BusBookmark> registerBookmark(
        @RequestBody BookMarkDTO bookMarkDTO
    ) throws IllegalAccessException;

    @Operation(summary = "관심노선 등록")
    @PostMapping("/{bookmarkId}")
    ResponseEntity<Void> deleteBookmark(
        @PathVariable("bookmarkId") Long bookmarkId
    );

    @Operation(summary = "관심노선목록 불러오기")
    @GetMapping("/{userId}")
    ResponseEntity<List<BusBookmark>> getBookmarks(
        @PathVariable Long userId
    );
}
