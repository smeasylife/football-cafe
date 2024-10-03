package football.cafe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInForm {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
