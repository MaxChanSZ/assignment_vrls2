package sg.lta.vrls2.repo;

import org.springframework.data.repository.CrudRepository;
import sg.lta.vrls2.model.VehicleRecord;


public interface VehicleRecordRepository extends CrudRepository<VehicleRecord, Long > {
}
