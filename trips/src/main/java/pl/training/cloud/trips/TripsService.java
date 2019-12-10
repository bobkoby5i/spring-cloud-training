package pl.training.cloud.trips;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.training.cloud.trips.drivers.Driver;
import pl.training.cloud.trips.drivers.DriversService;
import pl.training.cloud.trips.payments.Card;
import pl.training.cloud.trips.payments.Payment;
import pl.training.cloud.trips.payments.PaymentStatus;
import pl.training.cloud.trips.payments.PaymentsService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class TripsService {

    private final static long BASE_CHARGE = 1_000L;

    private final TripsRepository tripsRepository;
    private final PaymentsService paymentsService;
    private final DriversService driversService;

    public Trip startTrip(Long driverId) {
        Driver driver = driversService.getDriverById(driverId);
        Trip trip = new Trip(LocalDateTime.now(), driver);
        trip.setPayment(new Payment(PaymentStatus.NOT_STARTED));
        return tripsRepository.saveAndFlush(trip);
    }

    public Trip endTrip(Long tripId) {
        Trip trip = tripsRepository.findById(tripId)
                .orElseThrow(TripNotFoundException::new);
        Payment payment = trip.getPayment();
        if (payment.getStatus() == PaymentStatus.NOT_STARTED) {
            trip.setEndTime(LocalDateTime.now());
            Card card = trip.getDriver().getCard();
            paymentsService.pay(BASE_CHARGE, card).ifPresent(payment::setTransactionId);
            tripsRepository.saveAndFlush(trip);
        }
        return trip;
    }

}
