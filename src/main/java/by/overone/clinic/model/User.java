package by.overone.clinic.model;

import lombok.Data;

@Data

public class User {
    private long id;
    private String login;
    private String password;
    private String email;

}
