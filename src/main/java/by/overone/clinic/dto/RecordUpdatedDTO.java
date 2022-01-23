package by.overone.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordUpdatedDTO {
    private long id_record;
    private String doctorType;
    private Date date;
    private Time time;
}
