package koreatechbus.swaggerapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import koreatechbus.domain.Bookmark;
import koreatechbus.dto.bookmark.BookMarkDTO;

@Tag(name = "북마크 api", description = "북마크 api")
public interface BookmarkApi {
    @Operation(summary = "관심노선 등록")
    @PostMapping("/register")
    ResponseEntity<Bookmark> registerBookmark(
        @RequestBody BookMarkDTO bookMarkDTO
    );
}
