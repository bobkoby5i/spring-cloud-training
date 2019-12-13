package pl.training.cloud.payments;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.training.cloud.payments.api.PaymentResponseDto;
import pl.training.cloud.payments.api.PaymentStatusDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-13T15:19:25+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.3 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentResponseDto toPaymentResponseDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();

        paymentResponseDto.setId( payment.getId() );
        paymentResponseDto.setStatus( paymentStatusToPaymentStatusDto( payment.getStatus() ) );

        return paymentResponseDto;
    }

    protected PaymentStatusDto paymentStatusToPaymentStatusDto(PaymentStatus paymentStatus) {
        if ( paymentStatus == null ) {
            return null;
        }

        PaymentStatusDto paymentStatusDto;

        switch ( paymentStatus ) {
            case STARTED: paymentStatusDto = PaymentStatusDto.STARTED;
            break;
            case CONFIRMED: paymentStatusDto = PaymentStatusDto.CONFIRMED;
            break;
            case FAILED: paymentStatusDto = PaymentStatusDto.FAILED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + paymentStatus );
        }

        return paymentStatusDto;
    }
}
