package sg.lta.vrls2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.lta.vrls2.model.Vehicle;

import java.util.Optional;


public interface VehicleRepository extends JpaRepository<Vehicle, Long > {
    Vehicle getByUuid(String s);

    Optional<Vehicle> findByUuid(String uuid);
}
