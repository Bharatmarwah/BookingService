package in.bm.BookingService.ENTITY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_code", nullable = false)
    @JsonIgnore
    private Booking booking;

    @Column(name = "show_seat_id", nullable = false)
    private Long showSeatId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingSeatStatus bookingSeatStatus;

    @Column(name = "lock_expiry")
    private LocalDateTime lockExpiry;
}
