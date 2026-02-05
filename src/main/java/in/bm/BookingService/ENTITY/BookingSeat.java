package in.bm.BookingService.ENTITY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "booking_seats",
        uniqueConstraints = @UniqueConstraint(columnNames = "show_seat_id")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_code", referencedColumnName = "bookingCode")
    @JsonIgnore
    private Booking booking;

    @Column(name = "show_seat_id", nullable = false)
    private Long showSeatId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingSeatStatus bookingSeatStatus;
}
