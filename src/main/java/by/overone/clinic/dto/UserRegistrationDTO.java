package by.overone.clinic.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String login;
    private String password;
    private String email;
}
