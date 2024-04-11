package koreatechbus.dto.etc;

import java.util.List;

import koreatechbus.domain.Bookmark;
import koreatechbus.enums.Role;

public record MainDTO(
    String name,
    String schoolId,
    Role role,
    List<Bookmark> bookmarks
) {

    public MainDTO(String name, String schoolId, Role role, List<Bookmark> bookmarks) {
        this.name = name;
        this.schoolId = schoolId;
        this.role = role;
        this.bookmarks = bookmarks;
    }
}
