package pl.training.cloud.payments;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Log
@Service
@RequiredArgsConstructor
public class FakePaymentsService {

    private final PaymentsProvider paymentsProvider;
    private final PendingPaymentsSource pendingPaymentsSource;

    private Random random = new Random();

    @HystrixCommand(fallbackMethod = "payFallback")
    @NotifyPaymentStatusChange
    public Payment pay(Long amount, CreditCard creditCard) {
        PaymentRequest paymentRequest = createPaymentRequest(amount, creditCard);
        return paymentsProvider.pay(paymentRequest);
    }

    // refactor -> put in proxy
    public Payment payFallback(Long amount, CreditCard creditCard) {
        PaymentRequest paymentRequest = createPaymentRequest(amount, creditCard);
        log.info("Adding payment to pending queue");
        Message<PaymentRequest> requestMessage = MessageBuilder.withPayload(paymentRequest).build();
        pendingPaymentsSource.output().send(requestMessage);
        Payment payment = new Payment();
        payment.setId(paymentRequest.getId());
        payment.setStatus(PaymentStatus.STARTED);
        return payment;
    }

    private PaymentRequest createPaymentRequest(Long amount, CreditCard creditCard) {
        Long paymentId = Math.abs(random.nextLong());
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setId(paymentId);
        paymentRequest.setAmount(amount);
        paymentRequest.setCreditCard(creditCard);
        return paymentRequest;
    }

}
