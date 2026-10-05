package sg.lta.vrls2.repo;

import org.springframework.data.repository.CrudRepository;
import sg.lta.vrls2.model.VehicleUser;


public interface VehicleUserRepository extends CrudRepository<VehicleUser, Long > {
    VehicleUser getByUuid(String s);
}
