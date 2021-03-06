package by.overone.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAllDataDTO {
    private String login;
    private String email;
    private String name;
    private String surname;
    private String doctorType;
}
