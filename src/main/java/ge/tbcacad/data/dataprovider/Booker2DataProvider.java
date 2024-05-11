package ge.tbcacad.data.dataprovider;

import ge.tbcacad.steps.booker.Booker2Steps;
import org.testng.annotations.DataProvider;

public class Booker2DataProvider {
    protected Booker2Steps booker2Steps;

    @DataProvider(name = "bookingData")
    public Object[][] bookingData() {
        booker2Steps = new Booker2Steps();

        return new Object[][]{
                {booker2Steps
                        .createBookingRequest
                        ("John", "Doe", 500, true, "2024-01-01", "2024-01-10", "Extra pillows", null)},
                {booker2Steps.
                        createBookingRequest("Jane", "Doe", 1000, false, "2024-02-15", "2024-02-20", "Breakfast", null)}
        };
    }
}
