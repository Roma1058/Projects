package roman.kononenko.busterminal.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StopRequest {
    @NotNull
    private String placeName;
}
