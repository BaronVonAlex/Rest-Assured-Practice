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
                        ("John",
                                "Doe",
                                500,
                                true,
                                "2024-02-07",
                                "2024-09-16",
                                "Extra pillows",
                                null)},
                {booker2Steps.
                        createBookingRequest
                        ("Gorgi",
                                "Awd",
                                1000,
                                false,
                                "2021-02-15",
                                "2022-04-21",
                                "Breakfast",
                                null)}
        };
    }
}
