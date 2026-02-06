package in.bm.BookingService.ENTITY;

public enum BookingStatus {
    PENDING_PAYMENT, // booking created, seats locked
    CONFIRMED,       // payment success, seats booked
    CANCELLED,       // user cancel / expiry
    FAILED           // payment gateway failure
}












































