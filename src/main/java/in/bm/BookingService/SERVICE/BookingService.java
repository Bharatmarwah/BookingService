package in.bm.BookingService.SERVICE;

import in.bm.BookingService.ENTITY.Booking;
import in.bm.BookingService.REPOSITORY.BookingRepo;
import in.bm.BookingService.REPOSITORY.BookingSeatRepo;
import in.bm.BookingService.REQUESTDTO.BookingRequestDTO;
import in.bm.BookingService.RESPONSEDTO.BookingResponseDTO;
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
    public BookingResponseDTO addBooking(BookingRequestDTO dto,String userId){
        Booking booking = new Booking();
        booking.setUserID(userId);
        bookingRepo.save(booking);
        return BookingResponseDTO
                .builder()
                .bookingCode()
                .bookingStatus()
                .currency()
                .movieCode()
                .showId()
                .showSeatsIds()
                .totalAmount()
                .build();
    }
}
