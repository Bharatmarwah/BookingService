package in.bm.BookingService.CONTROLLER;

import in.bm.BookingService.REQUESTDTO.BookingRequestDTO;
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

    private BookingService bookingService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponseDTO createBooking(@Valid BookingRequestDTO dto ,@RequestHeader("x-user-id")String userId){
        return bookingService.addBooking(dto,userId);
    }


}
