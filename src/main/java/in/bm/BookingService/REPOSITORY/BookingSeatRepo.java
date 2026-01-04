package in.bm.BookingService.REPOSITORY;

import in.bm.BookingService.ENTITY.BookingSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingSeatRepo extends JpaRepository<BookingSeat,Long> {
}
