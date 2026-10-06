package sg.lta.vrls2.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import sg.lta.vrls2.model.Vehicle;
import sg.lta.vrls2.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {

    private static final int PAGE_SIZE = 50;

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/all")
    public List<Vehicle> list() {
        //for now, return all vehicles. This won't scale well, but we will build step-by-step.
        return vehicleService.all();
    }

    @GetMapping("/{uuid}")
    public Vehicle one(@PathVariable String uuid) {
        return vehicleService.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
