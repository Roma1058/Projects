package roman.kononenko.busterminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import roman.kononenko.busterminal.entity.Address;
import roman.kononenko.busterminal.repository.AddressRepository;

import javax.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

@RestController
public class testController {


    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/getTime")
    public String getTime()
    {
        return Instant.now().toString();
    }

    @GetMapping("/getRandom")
    public Integer RandNum(@RequestParam(required = false, defaultValue = "0") Integer min, @Valid @RequestParam(defaultValue = "10") Integer max)
    {
        return new SplittableRandom().nextInt(min, max);
    }

    @GetMapping("/getAddresses")
    public List<String> getAddress()
    {
        List<Address> retList = new ArrayList<>(addressRepository.findAll());

        List<String> returnList = new ArrayList<>();
        retList.forEach(i -> returnList.add(i.getId().toString() + ". " + i.getStreet()));

        return returnList;
    }
}

