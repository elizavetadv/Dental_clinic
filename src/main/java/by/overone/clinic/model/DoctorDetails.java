package by.overone.clinic.model;

import by.overone.clinic.util.validation.DoctorDetailsValidator;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Validated
public class DoctorDetails {
    private long doctor_user_id;

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
