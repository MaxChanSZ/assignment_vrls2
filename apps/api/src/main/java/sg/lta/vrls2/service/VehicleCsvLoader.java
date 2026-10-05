package sg.lta.vrls2.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.lta.vrls2.model.Vehicle;
import sg.lta.vrls2.model.VehicleRecord;
import sg.lta.vrls2.model.VehicleUser;
import sg.lta.vrls2.repo.VehicleRecordRepository;
import sg.lta.vrls2.repo.VehicleRepository;
import sg.lta.vrls2.repo.VehicleUserRepository;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class VehicleCsvLoader {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("[HH:mm:ss]"
            + "[H:mm:ss]");

    private VehicleRecord.RecordCategory currentRecordCategory;

    @Autowired
    private VehicleRecordRepository vehicleRecordRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleUserRepository vehicleUserRepository;

    public List<Vehicle> loadAll(Path dataDir) throws IOException, CsvException {
        List<Vehicle> result = new ArrayList<>();
        currentRecordCategory = VehicleRecord.RecordCategory.PERSONAL;
        result.addAll(load(dataDir.resolve("personal.csv"), "personal"));
        result.addAll(load(dataDir.resolve("deregpersonal.csv"), "personal"));

        currentRecordCategory = VehicleRecord.RecordCategory.COMMERCIAL;
        result.addAll(load(dataDir.resolve("commercial.csv"), "commercial"));
        result.addAll(load(dataDir.resolve("deregcommercial.csv"), "commercial"));
        return result;
    }

    private List<Vehicle> load(Path path, String category) throws IOException, CsvException {
        List<Vehicle> rows = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(path);
             CSVReader csv = new CSVReader(reader)) {
            List<String[]> all = csv.readAll();
            for (int i = 1; i < all.size(); i++) {
                String[] row = all.get(i);

                Vehicle currentVehicle = vehicleRepository.getByUuid(row[0]);

                if (currentVehicle == null) {
                    currentVehicle = new Vehicle();
                    currentVehicle.setUuid(row[0]);
                    currentVehicle.setBrand(row[6]);
                    currentVehicle.setType(row[7]);
                    currentVehicle.setRegistrationStatus(Vehicle.RegistrationStatus.valueOf(row[1]));
                    vehicleRepository.save(currentVehicle);
                }

                VehicleUser currentVehicleUser = vehicleUserRepository.getByUuid(row[5]);

                if (currentVehicleUser == null) {
                    currentVehicleUser = new VehicleUser();
                    currentVehicleUser.setUuid(row[5]);
                    vehicleUserRepository.save(currentVehicleUser);
                }

                VehicleRecord currentVehicleRecord = new VehicleRecord();
                currentVehicleRecord.setRecordCategory(currentRecordCategory);
                currentVehicleRecord.setStartDate(parseDate(row[2]));
                currentVehicleRecord.setEndDate(parseDate(row[3]));
                currentVehicleRecord.setTime(parseTime(row[4]));
                if (currentVehicleRecord.getTime() == null) {

                }
                currentVehicleRecord.setVehicle(currentVehicle);
                currentVehicleRecord.setVehicleUser(currentVehicleUser);
                currentVehicleRecord.setRecordType(
                        Vehicle.RegistrationStatus.valueOf(row[1])
                                .equals(Vehicle.RegistrationStatus.REGISTERED)
                                ? VehicleRecord.RecordType.REGISTRATION
                                : VehicleRecord.RecordType.DEREGISTRATION);
                vehicleRecordRepository.save(currentVehicleRecord);
            }
        }
        return rows;
    }

    LocalDate parseDate(String raw) {
        try {
            return LocalDate.parse(raw, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    LocalTime parseTime(String raw) {
        try {
            return LocalTime.parse(raw, TIME_FORMAT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
