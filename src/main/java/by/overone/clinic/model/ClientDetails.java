package by.overone.clinic.model;

import lombok.Data;

@Data
public class ClientDetails {
    private long client_user_id;
    private String name;
    private String surname;
    private String address;
    private String dataBirth;
    private String phoneNumber;
}
