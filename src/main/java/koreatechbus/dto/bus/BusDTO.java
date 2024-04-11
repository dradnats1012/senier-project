package koreatechbus.dto.bus;

import java.util.List;

import koreatechbus.domain.Station;
import koreatechbus.enums.Days;

public record BusDTO (
    Long busId,
    String busName
) {
    public static BusDTO of(Long busId, String busName) {
        return new BusDTO(busId, busName);
    }
}
