package koreatechbus.repository;

import java.util.List;

import koreatechbus.domain.Bus;
import koreatechbus.domain.Station;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
    Station save(Station station);
    
    List<Station> findStationsByBus(Bus bus);
}
