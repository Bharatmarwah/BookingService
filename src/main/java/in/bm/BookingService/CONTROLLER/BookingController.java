package in.bm.BookingService.CONTROLLER;

import in.bm.BookingService.REQUESTDTO.BookingRequestDTO;
import in.bm.BookingService.RESPONSEDTO.BookingResponseDTO;
import in.bm.BookingService.SERVICE.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private BookingService bookingService;

    public ResponseEntity<BookingResponseDTO> addBooking(@Valid
                                                      @RequestBody BookingRequestDTO requestDTO, @RequestHeader("x-user-id")String userId){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.addBooking(requestDTO,userId));
    }
}
