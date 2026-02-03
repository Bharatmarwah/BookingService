package in.bm.BookingService.SERVICE;

import in.bm.BookingService.CLIENT.MovieClient;
import in.bm.BookingService.ENTITY.Booking;
import in.bm.BookingService.ENTITY.BookingSeat;
import in.bm.BookingService.ENTITY.BookingStatus;
import in.bm.BookingService.REPOSITORY.BookingRepo;
import in.bm.BookingService.REPOSITORY.BookingSeatRepo;
import in.bm.BookingService.REQUESTDTO.InternalShowRequestDTO;
import in.bm.BookingService.RESPONSEDTO.BookingResponseDTO;
import in.bm.BookingService.RESPONSEDTO.InternalShowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final MovieClient movieClient;
    private final BookingRepo bookingRepo;
    private final BookingSeatRepo bookingSeatRepo;

    @Transactional
    public BookingResponseDTO addBooking(InternalShowRequestDTO dto) {

        InternalShowResponse showResponse = movieClient.validateShow(dto);

        if (showResponse == null) {
            throw new IllegalStateException("Show validation failed");
        }

        Booking booking = new Booking();
        booking.setBookingCode(generateBookingCode());
        booking.setUserID("userid2323");
        booking.setShowId(showResponse.getShowId());
        booking.setBookingStatus(BookingStatus.RESERVED);
        booking.setCurrency("INR");
        booking.setTotalAmount(showResponse.getTotalAmount());

        booking = bookingRepo.save(booking);

        List<BookingSeat> bookingSeats = new ArrayList<>();
        for (Long showSeatId : showResponse.getShowSeatsIds()) {
            BookingSeat bs = new BookingSeat();
            bs.setBooking(booking);
            bs.setShowSeatId(showSeatId);
            bookingSeats.add(bs);
        }

        bookingSeatRepo.saveAll(bookingSeats);

        return BookingResponseDTO.builder()
                .bookingCode(booking.getBookingCode())
                .bookingStatus(booking.getBookingStatus())
                .movieCode(showResponse.getMovieCode())
                .showId(showResponse.getShowId())
                .showSeatsIds(new ArrayList<>(showResponse.getShowSeatsIds()))
                .baseAmount(showResponse.getBaseAmount())
                .convenienceFee(showResponse.getConvenienceFee())
                .gstAmount(showResponse.getGstAmount())
                .totalAmount(booking.getTotalAmount())
                .currency(booking.getCurrency())
                .build();
    }

    private String generateBookingCode() {
        return "BK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
