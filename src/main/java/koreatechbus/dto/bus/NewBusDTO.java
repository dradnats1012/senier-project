package koreatechbus.dto.bus;

import java.util.List;

import koreatechbus.enums.Days;
public record NewBusDTO(
    String name,
    String departTime,
    String arrivalTime,
    List<Days> runDays
) {
}
