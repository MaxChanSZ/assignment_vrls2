package sg.lta.vrls2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.lta.vrls2.model.Vehicle;


public interface VehicleRepository extends JpaRepository<Vehicle, Long > {
    Vehicle getByUuid(String s);
}
