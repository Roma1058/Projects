package roman.kononenko.chat_se.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequest {

    @NotBlank
    private String username;

    @NotBlank
    @Size(min=3, max=30)
    private String password;
}
