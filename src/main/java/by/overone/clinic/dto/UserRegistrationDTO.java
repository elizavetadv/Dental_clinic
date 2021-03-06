package by.overone.clinic.dto;

import by.overone.clinic.util.validation.UserValidator;
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
public class UserRegistrationDTO {
    @NotNull
    @NotBlank
    @Pattern(regexp = UserValidator.LOGIN_REGEX)
    private String login;

    @NotNull
    @NotBlank
    @Pattern(regexp = UserValidator.PASSWORD_REGEX)
    private String password;

    @NotNull
    @NotBlank
    @Pattern(regexp = UserValidator.EMAIL_REGEX)
    private String email;
}
