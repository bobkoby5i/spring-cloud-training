package pl.training.cloud.payments;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PendingPaymentsSource {

    String PENDING_PAYMENTS = "pendingPayments";

    @Output(PENDING_PAYMENTS)
    MessageChannel output();

}
