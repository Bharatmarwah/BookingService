package in.bm.BookingService.ENTITY;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    private String bookingCode;

    @Column(nullable = false)
    private String userID;

    @Column(nullable = false)
    private Long showId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(
    mappedBy = "booking",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<BookingSeat> bookingSeat;

}
