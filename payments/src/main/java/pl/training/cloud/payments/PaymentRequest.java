package pl.training.cloud.payments;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long id;
    private Long amount;
    private CreditCard creditCard;

}
