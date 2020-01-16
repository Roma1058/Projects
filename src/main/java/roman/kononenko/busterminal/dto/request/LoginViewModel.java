package roman.kononenko.busterminal.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginViewModel {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
