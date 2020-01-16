package roman.kononenko.busterminal.service;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import roman.kononenko.busterminal.dto.request.PaginationRequest;
import roman.kononenko.busterminal.dto.request.StopRequest;
import roman.kononenko.busterminal.dto.response.PageResponse;
import roman.kononenko.busterminal.dto.response.StopResponse;
import roman.kononenko.busterminal.entity.Stop;
import roman.kononenko.busterminal.repository.StopRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class
StopService {
    @Autowired
    private StopRepository stopRepository;

    public void create(StopRequest stopRequest) {
        Stop stop = new Stop();
        stop.setPlaceName(stopRequest.getPlaceName());
        stopRepository.save(stop);
    }

    public List<StopResponse> findAll() {
        return stopRepository.findAll().stream().
                map(StopResponse::new).collect(Collectors.toList());
    }

    public PageResponse<StopResponse> findPage(PaginationRequest paginationRequest)
    {
        Page<Stop> page = stopRepository.findAll(paginationRequest.toPageable());
        return new PageResponse<>( page.get().map(StopResponse::new).collect(Collectors.toList()),
                page.getTotalPages(), page.getTotalElements());
    }

    public Long getLastId() {
        List<StopResponse> allStop = findAll();
        return allStop.get(allStop.size() - 1).getId();
    }

    public void delete(Long id)
    {
        stopRepository.deleteById(id);
    }

    public void update(Long id, StopRequest stopRequest) {

        stopRepository.save(stopRequestToStop(stopRepository.getOne(id), stopRequest));
    }

    private Stop stopRequestToStop(Stop stop, StopRequest stopRequest) {
        if (stop == null) {
            stop = new Stop();
        }
        stop.setPlaceName(stopRequest.getPlaceName());
        return stop;
    }
}
