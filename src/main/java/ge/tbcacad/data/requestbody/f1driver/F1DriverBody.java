package ge.tbcacad.data.requestbody.f1driver;

import ge.tbcacad.data.models.f1.responses.DriversItem;

public class F1DriverBody {
    public static DriversItem createExpectedDriver() {
        DriversItem expectedDriver = new DriversItem();
        expectedDriver.setDriverId("alonso");
        expectedDriver.setPermanentNumber("14");
        expectedDriver.setCode("ALO");
        expectedDriver.setUrl("http://en.wikipedia.org/wiki/Fernando_Alonso");
        expectedDriver.setGivenName("Fernando");
        expectedDriver.setFamilyName("Alonso");
        expectedDriver.setDateOfBirth("1981-07-29");
        expectedDriver.setNationality("Spanish");
        return expectedDriver;
    }
}
