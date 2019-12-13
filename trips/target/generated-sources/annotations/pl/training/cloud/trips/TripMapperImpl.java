package pl.training.cloud.trips;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-13T15:17:37+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.3 (Oracle Corporation)"
)
@Component
public class TripMapperImpl implements TripMapper {

    @Override
    public Trip toTrip(TripDto tripDto) {
        if ( tripDto == null ) {
            return null;
        }

        Trip trip = new Trip();

        trip.setStartTime( tripDto.getStartTime() );
        trip.setEndTime( tripDto.getEndTime() );

        return trip;
    }

    @Override
    public TripDto toTripDto(Trip trip) {
        if ( trip == null ) {
            return null;
        }

        TripDto tripDto = new TripDto();

        tripDto.setStartTime( trip.getStartTime() );
        tripDto.setEndTime( trip.getEndTime() );

        return tripDto;
    }
}
