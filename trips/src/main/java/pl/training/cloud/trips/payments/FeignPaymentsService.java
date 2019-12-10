package pl.training.cloud.trips.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.training.cloud.payments.api.PaymentResponseDto;
import pl.training.cloud.payments.api.Payments;
import pl.training.cloud.payments.api.PaymentsRequestDto;

import java.util.Optional;

@Primary
@Log
@Service
@RequiredArgsConstructor
public class FeignPaymentsService implements PaymentsService {

    private final Payments payments;
    private final PaymentMapper paymentMapper;

    @Override
    public Optional<Long> pay(Long amount, Card card) {
        PaymentsRequestDto paymentsRequestDto = paymentMapper.toPaymentRequestDto(card);
        paymentsRequestDto.setAmount(amount);
        try {
            PaymentResponseDto paymentResponseDto = payments.addPaymentRequest(paymentsRequestDto).getBody();
            if (paymentResponseDto != null) {
                return Optional.ofNullable(paymentResponseDto.getId());
            }
        } catch (HttpClientErrorException exception) {
            log.warning("Payment failed");
        }
        return Optional.empty();
    }

}
