package pl.training.cloud.payments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("payments")
@RestController
public class PaymentsController {

    @GetMapping
    public String test() {
        return "OK";
    }

}
