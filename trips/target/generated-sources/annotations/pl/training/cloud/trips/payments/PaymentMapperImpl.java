package pl.training.cloud.trips.payments;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.training.cloud.payments.api.CardDto;
import pl.training.cloud.payments.api.PaymentResponseDto;
import pl.training.cloud.payments.api.PaymentStatusDto;
import pl.training.cloud.payments.api.PaymentsRequestDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-11T16:05:57+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.3 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentsRequestDto toPaymentRequestDto(Card card) {
        if ( card == null ) {
            return null;
        }

        PaymentsRequestDto paymentsRequestDto = new PaymentsRequestDto();

        paymentsRequestDto.setCard( cardToCardDto( card ) );

        return paymentsRequestDto;
    }

    @Override
    public Payment toPayment(PaymentResponseDto paymentResponseDto) {
        if ( paymentResponseDto == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setTransactionId( paymentResponseDto.getId() );
        payment.setId( paymentResponseDto.getId() );
        payment.setStatus( paymentStatusDtoToPaymentStatus( paymentResponseDto.getStatus() ) );

        return payment;
    }

    protected CardDto cardToCardDto(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDto cardDto = new CardDto();

        cardDto.setNumber( card.getNumber() );
        cardDto.setCvv( card.getCvv() );
        cardDto.setExpirationDate( card.getExpirationDate() );

        return cardDto;
    }

    protected PaymentStatus paymentStatusDtoToPaymentStatus(PaymentStatusDto paymentStatusDto) {
        if ( paymentStatusDto == null ) {
            return null;
        }

        PaymentStatus paymentStatus;

        switch ( paymentStatusDto ) {
            case STARTED: paymentStatus = PaymentStatus.STARTED;
            break;
            case CONFIRMED: paymentStatus = PaymentStatus.CONFIRMED;
            break;
            case FAILED: paymentStatus = PaymentStatus.FAILED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + paymentStatusDto );
        }

        return paymentStatus;
    }
}
