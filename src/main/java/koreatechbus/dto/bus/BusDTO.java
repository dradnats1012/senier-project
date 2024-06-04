package koreatechbus.dto.bus;

public record BusDTO(
    Long busId,
    String busName,
    Boolean isRun) {
    public static BusDTO of(Long busId, String busName, Boolean isRun) {
        return new BusDTO(busId, busName, isRun);
    }
}
