package sg.lta.vrls2.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "vehicle_record")
public class VehicleRecord {

        public enum RecordCategory {
                COMMERCIAL,
                PERSONAL
        }

        public enum RecordType {
                REGISTRATION,
                DEREGISTRATION
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "vehicle_id")
        private Vehicle vehicle;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "vehicle_user_id")
        private VehicleUser vehicleUser;

        @Enumerated(EnumType.STRING)
        @Column(name = "record_category")
        private RecordCategory recordCategory;

        @Enumerated(EnumType.STRING)
        @Column(name = "record_type")
        private RecordType recordType;

        @Column(name = "start_date")
        private LocalDate startDate;

        @Column(name = "end_date")
        private LocalDate endDate;

        @Column(name = "time")
        private LocalTime time;

        public Long getId() {
                return id;
        }

        public Vehicle getVehicle() {
                return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
                this.vehicle = vehicle;
        }

        public VehicleUser getVehicleUser() {
                return vehicleUser;
        }

        public void setVehicleUser(VehicleUser vehicleUser) {
                this.vehicleUser = vehicleUser;
        }

        public RecordCategory getRecordCategory() {
                return recordCategory;
        }

        public void setRecordCategory(RecordCategory recordCategory) {
                this.recordCategory = recordCategory;
        }

        public RecordType getRecordType() {
                return recordType;
        }

        public void setRecordType(RecordType recordType) {
                this.recordType = recordType;
        }

        public LocalDate getStartDate() {
                return startDate;
        }

        public void setStartDate(LocalDate startDate) {
                this.startDate = startDate;
        }

        public LocalDate getEndDate() {
                return endDate;
        }

        public void setEndDate(LocalDate endDate) {
                this.endDate = endDate;
        }

        public LocalTime getTime() {
                return time;
        }

        public void setTime(LocalTime time) {
                this.time = time;
        }
}