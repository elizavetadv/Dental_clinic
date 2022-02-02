package by.overone.clinic.model;

import lombok.Data;

@Data
public class DoctorDetails {
    private long doctor_user_id;
    private String name;
    private String surname;
    private String doctorType;
}
