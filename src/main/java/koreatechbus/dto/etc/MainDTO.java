package koreatechbus.dto.etc;

import java.util.List;

import koreatechbus.domain.BusBookmark;
import koreatechbus.enums.Role;

public record MainDTO(
    String name,
    String schoolId,
    Role role,
    List<BusBookmark> busBookmarks
) {

    public MainDTO(String name, String schoolId, Role role, List<BusBookmark> busBookmarks) {
        this.name = name;
        this.schoolId = schoolId;
        this.role = role;
        this.busBookmarks = busBookmarks;
    }
}
