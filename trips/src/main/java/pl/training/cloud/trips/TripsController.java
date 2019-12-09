package pl.training.cloud.trips;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("trips")
@RestController
public class TripsController {

    @GetMapping
    public String test() {
        return "OK";
    }

}
