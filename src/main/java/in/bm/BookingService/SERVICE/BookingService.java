package in.bm.BookingService.SERVICE;

import in.bm.BookingService.REPOSITORY.BookingRepo;
import in.bm.BookingService.REPOSITORY.BookingSeatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private BookingRepo bookingRepo;
    private BookingSeatRepo bookingSeatRepo;

    // webClient call a api
    // request send including showId and seatNumbers
    // verifies showId and extract movieCode and screen from which seatNumbers per prize and theater code

}
