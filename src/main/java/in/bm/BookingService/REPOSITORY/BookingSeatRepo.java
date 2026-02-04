package in.bm.BookingService.REPOSITORY;

import in.bm.BookingService.ENTITY.Booking;
import in.bm.BookingService.ENTITY.BookingSeat;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingSeatRepo extends JpaRepository<BookingSeat,Long> {

    boolean existsByShowSeatIdIn(List<Long> showSeatIds);

    BookingSeat findByBooking(Booking booking);
}
