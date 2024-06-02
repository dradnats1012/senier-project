package koreatechbus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import koreatechbus.domain.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
    List<Bus> findAll();

    Bus findByBusId(Long busId);
}
