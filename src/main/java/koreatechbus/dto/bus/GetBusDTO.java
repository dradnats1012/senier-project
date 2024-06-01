package koreatechbus.dto.bus;

import java.util.List;

import koreatechbus.domain.Station;
import koreatechbus.enums.Days;

public record GetBusDTO(
    List<Days> runDays,
    List<Station> stations,
    double latitude,
    double longitude
) {
    public static GetBusDTO of(List<Days> runDays, List<Station> stations, double latitude, double longitude) {
        return new GetBusDTO(runDays, stations, latitude, longitude);
    }
}
