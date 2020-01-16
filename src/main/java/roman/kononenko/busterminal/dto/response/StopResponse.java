package roman.kononenko.busterminal.dto.response;

import lombok.Getter;
import lombok.Setter;
import roman.kononenko.busterminal.entity.Stop;

@Getter
@Setter
public class StopResponse {

    private Long id;

    private String placeName;

    public StopResponse(Stop stop)
    {
        id = stop.getId();
        placeName = stop.getPlaceName();
    }
}
