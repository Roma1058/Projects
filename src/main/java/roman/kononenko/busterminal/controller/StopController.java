package roman.kononenko.busterminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roman.kononenko.busterminal.dto.request.PaginationRequest;
import roman.kononenko.busterminal.dto.request.StopRequest;
import roman.kononenko.busterminal.dto.response.PageResponse;
import roman.kononenko.busterminal.dto.response.StopResponse;
import roman.kononenko.busterminal.service.StopService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/stop")
public class StopController {
    @Autowired
    private StopService stopService;

    @GetMapping
    public List<StopResponse> findAll(){

        return stopService.findAll();
    }

    @PostMapping
    public Long addStop(@Valid String stopName){
        StopRequest stopRequest = new StopRequest();
        stopRequest.setPlaceName(stopName);

        stopService.create(stopRequest);

        return stopService.getLastId();
    }

    @PutMapping
    public void update(Long id, @Valid String stopNameUpdate){
        StopRequest stopRequest = new StopRequest();
        stopRequest.setPlaceName(stopNameUpdate);

        stopService.update(id, stopRequest);
    }

    @PostMapping("/page")
    public PageResponse<StopResponse> getPage(@Valid PaginationRequest paginationRequest)
    {
        return stopService.findPage(paginationRequest);
    }

    @DeleteMapping
    public void delete(@Valid Long id)
    {
        stopService.delete(id);
    }
}
