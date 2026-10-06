package sg.lta.vrls2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.lta.vrls2.model.VehicleUser;


public interface VehicleUserRepository extends JpaRepository<VehicleUser, Long > {
    VehicleUser getByUuid(String s);
}
