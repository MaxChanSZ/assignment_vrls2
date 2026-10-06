package sg.lta.vrls2.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sg.lta.vrls2.model.Vehicle;
import sg.lta.vrls2.repo.VehicleRepository;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleCsvLoader loader;

    @Value("${data.directory}")
    private String dataDir;

    @Autowired
    public VehicleService(VehicleCsvLoader loader) {
        this.loader = loader;
    }

    @Autowired
    public VehicleRepository vehicleRepository;

    @PostConstruct
    public void init() throws Exception {
        Path path = Paths.get(dataDir).toAbsolutePath().normalize();
        loader.loadAll(path);
    }

    public List<Vehicle> all() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> findByUuid(String uuid) {
        return vehicleRepository.findByUuid(uuid);
    }
}
