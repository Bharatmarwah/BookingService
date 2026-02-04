package in.bm.BookingService.CONTROLLER;

import in.bm.BookingService.REQUESTDTO.InternalShowRequestDTO;
import in.bm.BookingService.RESPONSEDTO.BookingResponseDTO;
import in.bm.BookingService.SERVICE.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponseDTO createBooking(@RequestBody InternalShowRequestDTO dto, @RequestHeader("x-user-id")String userId){
        return bookingService.addBooking(dto,userId);
    }

    @DeleteMapping("/{bookingCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelBooking(@PathVariable String bookingCode){
        bookingService.cancelBooking(bookingCode);
    }

}
