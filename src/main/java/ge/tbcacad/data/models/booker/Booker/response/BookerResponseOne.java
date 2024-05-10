package ge.tbcacad.data.models.booker.Booker.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookerResponseOne {

    @JsonProperty("booking")
    private Booking booking;

    @JsonProperty("bookingid")
    private int bookingid;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    @Override
    public String toString() {
        return
                "BookerResponseOne{" +
                        "booking = '" + booking + '\'' +
                        ",bookingid = '" + bookingid + '\'' +
                        "}";
    }
}