package by.overone.clinic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class ClientRecord {
    private long clientRecordId;
    private String doctorSurname;
    private String doctorType;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Time time;
    private long clientId;
}
