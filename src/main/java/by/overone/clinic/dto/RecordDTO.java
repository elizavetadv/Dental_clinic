package by.overone.clinic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class RecordDTO {
    @NotNull
    @NotBlank
    @Pattern(regexp = "surgeon|orthodontist|orthopedist")
    private String doctorType;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Pattern(regexp = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])")
    private LocalDate date;

    @NotNull
//    @Pattern(regexp = "8:00:00|9:00:00|10:00:00|11:00:00|12:00:00|14:00:00|15:00:00|16:00:00")
    private Time time;
}
