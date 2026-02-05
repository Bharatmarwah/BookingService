package in.bm.BookingService.SERVICE;

import in.bm.BookingService.CLIENT.MovieClient;
import in.bm.BookingService.ENTITY.Booking;
import in.bm.BookingService.ENTITY.BookingSeat;
import in.bm.BookingService.ENTITY.BookingSeatStatus;
import in.bm.BookingService.ENTITY.BookingStatus;
import in.bm.BookingService.EXCEPTION.BookingNotFoundException;
import in.bm.BookingService.EXCEPTION.SeatAlreadyBookedException;
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
    public BookingResponseDTO addBooking(InternalShowRequestDTO dto, String userId) {

        boolean seatAlreadyTaken = bookingSeatRepo
                .existsByShowSeatIdInAndBookingSeatStatusNot
                        (dto.getShowSeatIds(), BookingSeatStatus.AVAILABLE);

        if (seatAlreadyTaken){
            throw new SeatAlreadyBookedException("One or more seats already booked");
        }

        InternalShowResponse showResponse = movieClient.validateShow(dto);

        if (showResponse == null) {
            throw new IllegalStateException("Show validation failed");
        }

        Booking booking = new Booking();
        booking.setBookingCode(generateBookingCode());
        booking.setUserID(userId);
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
            bs.setBookingSeatStatus(BookingSeatStatus.LOCKED);
            bookingSeats.add(bs);
        }

        bookingSeatRepo.saveAll(bookingSeats);

        return BookingResponseDTO.builder()
                .bookingCode(booking.getBookingCode())
                .movieCode(showResponse.getMovieCode())
                .showId(booking.getShowId())
                .showSeatsIds(showResponse.getShowSeatsIds())
                .currency(booking.getCurrency())
                .baseAmount(showResponse.getBaseAmount())
                .convenienceFee(showResponse.getConvenienceFee())
                .gstAmount(showResponse.getGstAmount())
                .totalAmount(showResponse.getTotalAmount())
                .bookingStatus(booking.getBookingStatus())
                .build();
    }

    private String generateBookingCode() {
        return "BK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    @Transactional
    public void cancelBooking(String bookingCode) {
       Booking booking = bookingRepo
               .findById(bookingCode)
               .orElseThrow(()->
                       new BookingNotFoundException("Booking not found"));

       booking.setBookingStatus(BookingStatus.CANCELLED);
    }
}
