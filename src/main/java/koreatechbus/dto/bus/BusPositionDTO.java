package koreatechbus.dto.bus;

public record BusPositionDTO(
    Double latitude,
    Double longitude
) {
    public static BusPositionDTO of(Double latitude, Double longitude){
        return new BusPositionDTO(latitude, longitude);
    }
}
