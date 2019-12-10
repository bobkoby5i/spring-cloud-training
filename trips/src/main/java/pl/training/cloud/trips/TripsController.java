package pl.training.cloud.trips;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("trips")
@RestController
@RequiredArgsConstructor
public class TripsController {

    private final TripsService tripsService;
    private final TripMapper tripMapper;


    @GetMapping
    public String test() {
        return "OK";
    }

}
