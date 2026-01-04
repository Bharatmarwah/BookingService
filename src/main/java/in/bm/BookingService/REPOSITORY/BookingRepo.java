package in.bm.BookingService.REPOSITORY;

import in.bm.BookingService.ENTITY.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking,String> {
}
