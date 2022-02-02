package by.overone.clinic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class DoctorTimetable {
    private long id;
    private String clientSurname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Time time;
    private long doctorId;
    private String status;
}
