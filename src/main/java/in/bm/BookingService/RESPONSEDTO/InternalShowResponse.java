package in.bm.BookingService.RESPONSEDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter@Builder
public class InternalShowResponse {

    private String movieCode;
    private Long showId;
    private List<Long> showSeatsIds;
    private double baseAmount;
    private double convenienceFee;
    private double gstAmount;
    private double totalAmount;

}
