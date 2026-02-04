package in.bm.BookingService.RESPONSEDTO;

import java.time.LocalDateTime;

public record ApiErrorResponse(String error,
                               String message,
                               int status,
                               LocalDateTime timestamp
) {
}
