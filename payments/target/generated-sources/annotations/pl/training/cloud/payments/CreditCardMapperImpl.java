package pl.training.cloud.payments;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.training.cloud.payments.api.CardDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-11T16:05:58+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.3 (Oracle Corporation)"
)
@Component
public class CreditCardMapperImpl implements CreditCardMapper {

    @Override
    public CreditCard toCreditCard(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        CreditCard creditCard = new CreditCard();

        creditCard.setExpiration( cardDto.getExpirationDate() );
        creditCard.setNumber( cardDto.getNumber() );
        creditCard.setCvv( cardDto.getCvv() );

        return creditCard;
    }
}
