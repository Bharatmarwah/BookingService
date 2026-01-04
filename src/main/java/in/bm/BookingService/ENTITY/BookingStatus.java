package in.bm.BookingService.ENTITY;

public enum BookingStatus {
    RESERVED,    // seats locked, awaiting payment
    CONFIRMED,   // payment successful
    CANCELLED,   // user/admin cancelled
    EXPIRED,     // payment timeout / auto-release
    FAILED       // payment failed
}
