package in.bm.BookingService.REPOSITORY;

import in.bm.BookingService.ENTITY.BookingSeat;
import in.bm.BookingService.ENTITY.BookingSeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingSeatRepo extends JpaRepository<BookingSeat,Long> {

    boolean existsByShowSeatIdInAndBookingSeatStatusNot(
            List<Long> showSeatIds,
            BookingSeatStatus status
    );
}
