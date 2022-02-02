package by.overone.clinic.dto;

import by.overone.clinic.util.validation.DoctorDetailsValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class DoctorDetailsDTO {
    @NotNull
    @NotBlank
    @Pattern(regexp = DoctorDetailsValidator.NAME_REGEX)
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = DoctorDetailsValidator.SURNAME_REGEX)
    private String surname;

    @NotNull
    @NotBlank
    @Pattern(regexp = "surgeon|orthodontist|orthopedist")
    private String doctorType;
}
