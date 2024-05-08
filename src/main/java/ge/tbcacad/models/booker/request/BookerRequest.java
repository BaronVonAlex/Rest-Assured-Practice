package ge.tbcacad.models.booker.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookerRequest {

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("additionalneeds")
    private String additionalneeds;

    @JsonProperty("bookingdates")
    private Bookingdates bookingdates;

    @JsonProperty("totalprice")
    private int totalprice;

    @JsonProperty("depositpaid")
    private boolean depositpaid;

    @JsonProperty("lastname")
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public Bookingdates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(Bookingdates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return
                "BookerRequest{" +
                        "firstname = '" + firstname + '\'' +
                        ",additionalneeds = '" + additionalneeds + '\'' +
                        ",bookingdates = '" + bookingdates + '\'' +
                        ",totalprice = '" + totalprice + '\'' +
                        ",depositpaid = '" + depositpaid + '\'' +
                        ",lastname = '" + lastname + '\'' +
                        "}";
    }
}