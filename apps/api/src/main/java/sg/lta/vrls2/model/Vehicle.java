package sg.lta.vrls2.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vehicle")
public class Vehicle {

        public enum RegistrationStatus {
                REGISTERED,
                DEREGISTERED
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "uuid")
        private String uuid;

        @Enumerated(EnumType.STRING)
        @Column(name = "registration_status")
        private RegistrationStatus registrationStatus;

        @Enumerated(EnumType.STRING)
        @Column(name = "vehicle_category")
        private VehicleRecord.RecordCategory recordCategory;

        @Column(name = "registration_start_date")
        private LocalDate startDate;

        @Column(name = "registration_end_date")
        private LocalDate endDate;

        @Column(name = "brand")
        private String brand;

        @Column(name = "type")
        private String type;

        public Long getId() {
                return id;
        }

        public String getUuid() {
                return uuid;
        }

        public void setUuid(String uuid) {
                this.uuid = uuid;
        }

        public String getBrand() {
                return brand;
        }

        public void setBrand(String brand) {
                this.brand = brand;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public RegistrationStatus getRegistrationStatus() {
                return registrationStatus;
        }

        public void setRegistrationStatus(RegistrationStatus registrationStatus) {
                this.registrationStatus = registrationStatus;
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

        public VehicleRecord.RecordCategory getRecordCategory() {
                return recordCategory;
        }

        public void setRecordCategory(VehicleRecord.RecordCategory recordCategory) {
                this.recordCategory = recordCategory;
        }
}