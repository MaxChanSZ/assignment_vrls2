package sg.lta.vrls2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.lta.vrls2.model.VehicleRecord;


public interface VehicleRecordRepository extends JpaRepository<VehicleRecord, Long > {
}
