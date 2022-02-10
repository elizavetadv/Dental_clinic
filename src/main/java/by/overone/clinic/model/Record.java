package by.overone.clinic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class Record {
    private long idRecord;
    private String doctorType;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Time time;
    private String status;
    private long user_user_id;
    private long doctor_id;
}
