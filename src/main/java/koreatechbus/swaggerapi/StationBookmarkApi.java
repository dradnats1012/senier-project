package koreatechbus.swaggerapi;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import koreatechbus.domain.StationBookmark;
import koreatechbus.dto.stationbookmark.StationBookmarkDTO;

@Tag(name = "정류장 북마크 api", description = "정류장 북마크 api")
public interface StationBookmarkApi {

    @Operation(summary = "관심 정류장 등록")
    @PostMapping("/register")
    ResponseEntity<StationBookmark> registerBookmark(
        @RequestBody StationBookmarkDTO stationBookMarkDTO
    ) throws IllegalAccessException;

    @Operation(summary = "관심 정류장 삭제")
    @PostMapping("/{bookmarkId}")
    ResponseEntity<Void> deleteBookmark(
        @PathVariable("bookmarkId") Long bookmarkId
    );

    @Operation(summary = "관심 정류장 목록 불러오기")
    @GetMapping("/{userId}")
    ResponseEntity<List<StationBookmark>> getBookmarks(
        @PathVariable Long userId
    );
}
