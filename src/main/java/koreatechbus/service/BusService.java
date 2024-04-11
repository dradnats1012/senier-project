package koreatechbus.service;

import koreatechbus.domain.Bus;
import koreatechbus.domain.Station;
import koreatechbus.dto.bus.BusDTO;
import koreatechbus.dto.bus.GetBusDTO;
import koreatechbus.dto.bus.NewBusDTO;
import koreatechbus.enums.Days;
import koreatechbus.repository.BusRepository;
import koreatechbus.repository.StationRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BusService {
    private final BusRepository busRepository;
    private final StationRepository stationRepository;
    private static final Long MANAGER_ROLE_NUM = 1L;

    public BusService(BusRepository busRepository, StationRepository stationRepository){
        this.busRepository = busRepository;
        this.stationRepository = stationRepository;
    }

    public List<BusDTO> getAllBus(){
        List<Bus> buses = busRepository.findAll();
        List<BusDTO> busDTOS = new ArrayList<>();

        for (Bus bus : buses) {
            busDTOS.add(BusDTO.of(bus.getBusId(), bus.getName()));
        }

        return busDTOS;
    }

    public Bus putBus(NewBusDTO newBusDTO){
        return busRepository.save(Bus.builder()
                .name(newBusDTO.name())
                .departTime(newBusDTO.departTime())
                .arrivalTime(newBusDTO.arrivalTime())
                .runDays(newBusDTO.runDays())
                .build());
    }

    private void isManager(Long role){
        if(!Objects.equals(role, MANAGER_ROLE_NUM)){
            throw new IllegalArgumentException("권한이 없습니다!");
        }
    }

    public GetBusDTO getBusById(Long busId){
        Bus bus = busRepository.findByBusId(busId);
        List<Days> runDays = bus.getRunDays();
        List<Station> stations = stationRepository.findStationsByBus(bus);

        return GetBusDTO.of(runDays, stations);
    }
}
