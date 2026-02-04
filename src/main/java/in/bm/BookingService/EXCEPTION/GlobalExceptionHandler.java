package in.bm.BookingService.EXCEPTION;

import in.bm.BookingService.RESPONSEDTO.ApiErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(SeatAlreadyBookedException.class)
    public ResponseEntity<ApiErrorResponse> handleSeatAlreadyBookedException(SeatAlreadyBookedException ex){
       return build(ex,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateEntry(DataIntegrityViolationException ex){
        return build(ex,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundBookingException(BookingNotFoundException ex){
        return build(ex,HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ApiErrorResponse> build(Exception ex, HttpStatus status) {

        ApiErrorResponse error = new ApiErrorResponse(
                status.getReasonPhrase(),
                ex.getMessage(),
                status.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(error);
    }


}
