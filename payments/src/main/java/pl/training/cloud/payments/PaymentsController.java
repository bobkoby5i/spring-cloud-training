package pl.training.cloud.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.cloud.payments.api.PaymentResponseDto;
import pl.training.cloud.payments.api.PaymentsRequestDto;

@RequestMapping("payments")
@RestController
@RequiredArgsConstructor
public class PaymentsController {

    private final FakePaymentsService fakePaymentsService;
    private final CreditCardMapper creditCardMapper;
    private final PaymentMapper paymentMapper;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> addPaymentRequest(@RequestBody PaymentsRequestDto paymentsRequestDto) {
        Long amount = paymentsRequestDto.getAmount();
        CreditCard creditCard = creditCardMapper.toCreditCard(paymentsRequestDto.getCard());
        Payment payment = fakePaymentsService.pay(amount, creditCard);
        PaymentResponseDto paymentResponseDto = paymentMapper.toPaymentResponseDto(payment);
        return ResponseEntity.accepted().body(paymentResponseDto);
    }

}
