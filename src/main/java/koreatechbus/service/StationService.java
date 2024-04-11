package koreatechbus.service;

import org.springframework.stereotype.Service;

import koreatechbus.domain.Bus;
import koreatechbus.domain.Station;
import koreatechbus.dto.station.RegisterStationDTO;
import koreatechbus.repository.BusRepository;
import koreatechbus.repository.StationRepository;

@Service
public class StationService {
    private final StationRepository stationRepository;
    private final BusRepository busRepository;

    public StationService(StationRepository stationRepository, BusRepository busRepository) {
        this.stationRepository = stationRepository;
        this.busRepository = busRepository;
    }

    public Station registerStation(RegisterStationDTO registerStationDTO) {
        Bus bus = busRepository.findByBusId(registerStationDTO.busId());

        Station station = Station.builder()
            .stationName(registerStationDTO.stationName())
            .arrivalTime(registerStationDTO.arrivalTime())
            .bus(bus)
            .build();

        stationRepository.save(station);

        return station;
    }
}
