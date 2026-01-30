package in.bm.BookingService.RESPONSEDTO;

import in.bm.BookingService.ENTITY.BookingStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BookingResponseDTO {

    private String bookingCode;
    private String movieCode;
    private Long showId;
    private List<Long> showSeatsIds;
    private String currency;
    private Double totalAmount;
    private BookingStatus bookingStatus;


}
