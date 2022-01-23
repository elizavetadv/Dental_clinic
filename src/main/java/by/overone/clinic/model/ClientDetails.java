package by.overone.clinic.model;

import by.overone.clinic.util.validation.ClientDetailsValidator;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Validated
public class ClientDetails {
    private long client_user_id;

    @NotNull
    @NotBlank
    @Pattern(regexp = ClientDetailsValidator.NAME_REGEX)
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = ClientDetailsValidator.SURNAME_REGEX)
    private String surname;

    @NotNull
    @NotBlank
    @Pattern(regexp = ClientDetailsValidator.ADDRESS_REGEX)
    private String address;

    @NotNull
    @NotBlank
    @Pattern(regexp = ClientDetailsValidator.DATA_BIRTH_REGEX)
    private String dataBirth;

    @NotNull
    @NotBlank
    @Pattern(regexp = ClientDetailsValidator.PHONE_NUMBER_REGEX)
    private String phoneNumber;
}
