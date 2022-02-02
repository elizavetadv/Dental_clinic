package by.overone.clinic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRecordDTO {
    private String doctor_surname;
    private String doctor_type;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Time time;
}
