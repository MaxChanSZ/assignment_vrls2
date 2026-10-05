package sg.lta.vrls2.repo;

import org.springframework.data.repository.CrudRepository;
import sg.lta.vrls2.model.Vehicle;
import sg.lta.vrls2.model.VehicleRecord;


public interface VehicleRepository extends CrudRepository<Vehicle, Long > {
    Vehicle getByUuid(String s);
}
