package in.bm.BookingService.CONTROLLER;

import in.bm.BookingService.REQUESTDTO.InternalShowRequestDTO;
import in.bm.BookingService.RESPONSEDTO.BookingResponseDTO;
import in.bm.BookingService.SERVICE.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponseDTO createBooking(@RequestBody InternalShowRequestDTO dto ){
        return bookingService.addBooking(dto);
    }
    //todo correct the showSeatId prize
    // todo correct order the add booking response
    // todo complete webclient
}
