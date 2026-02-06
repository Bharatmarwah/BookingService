package in.bm.BookingService.REPOSITORY;

import in.bm.BookingService.ENTITY.Booking;
import in.bm.BookingService.ENTITY.BookingSeat;
import in.bm.BookingService.ENTITY.BookingSeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingSeatRepo extends JpaRepository<BookingSeat, Long> {


    @Query("SELECT bs FROM BookingSeat bs WHERE bs.bookingSeatStatus ='LOCKED' AND bs.lockExpiry < :now")
    List<BookingSeat> findExpiredLockedSeats(@Param("now") LocalDateTime now);

    List<BookingSeat> findByBooking(Booking booking);

    @Query("""
            SELECT COUNT(bs) > 0
            FROM BookingSeat bs
            WHERE bs.showSeatId IN :seatIds
            AND bs.bookingSeatStatus IN ('LOCKED','BOOKED')
            AND (bs.lockExpiry IS NULL OR bs.lockExpiry > :now)
            """)
    boolean existsActiveSeat(
            @Param("seatIds") List<Long> seatIds,
            @Param("now") LocalDateTime now
    );
}
