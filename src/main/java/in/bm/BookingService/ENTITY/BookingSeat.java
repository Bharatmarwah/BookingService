package in.bm.BookingService.ENTITY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking_seats",
        uniqueConstraints = @UniqueConstraint
                (columnNames = {"showId","seatNumber"}))
public class BookingSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id",referencedColumnName = "bookingCode")
    @JsonIgnore
    private Booking booking;

    @Column(nullable = false)
    private Long showId;

    @Column(nullable = false)
    private String seatNumber;

}
