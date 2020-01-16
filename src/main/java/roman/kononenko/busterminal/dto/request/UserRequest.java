package roman.kononenko.busterminal.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequest {

    @NotBlank
    private String login;

    @NotBlank
    @Size(min=3, max=30)
    private String password;

    private String name;
    private String surname;
    private String email;
    private String sex;
}
