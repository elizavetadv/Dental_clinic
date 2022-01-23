package by.overone.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientAllDataDTO {
    private long user_id;
    private String login;
    private String email;
    private String name;
    private String surname;
    private String address;
    private String dataBirth;
    private String phoneNumber;
}
