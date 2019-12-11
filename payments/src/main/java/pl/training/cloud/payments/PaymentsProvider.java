package pl.training.cloud.payments;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Random;

@Log
@Service
public class PaymentsProvider {

    private final Random random = new Random();

    public Payment pay(PaymentRequest paymentRequest) {
        CreditCard creditCard = paymentRequest.getCreditCard();
        Long amount = paymentRequest.getAmount();
        log.info("Charging card: " + creditCard.getNumber() + " (amount: " + amount + ")");
        fakeDelay();
        Payment payment = new Payment();
        payment.setId(paymentRequest.getId());
        if (random.nextBoolean()) {
            payment.setStatus(PaymentStatus.CONFIRMED);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
        }
        return payment;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(random.nextInt(20_000));
        } catch (InterruptedException e) {
            log.info("Thread interrupted");
        }
    }

}
