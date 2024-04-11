package koreatechbus.dto.bus;

import java.util.List;

import koreatechbus.domain.Station;
import koreatechbus.enums.Days;

public record GetBusDTO (
    List<Days> runDays,
    List<Station> stations
){
    public static GetBusDTO of(List<Days> runDays, List<Station> stations) {
        return new GetBusDTO(runDays, stations);
    }
}
