package by.overone.clinic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class ClientRecord {
    private long client_record_id;
    private String doctor_surname;
    private String doctor_type;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Time time;
    private long client_details_client_user_id;
}
