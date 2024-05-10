package ge.tbcacad.f1;

import ge.tbcacad.data.models.f1.responses.DriversItem;
import ge.tbcacad.data.requestbody.f1driver.F1DriverBody;
import ge.tbcacad.steps.f1.F1Steps;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.ERGAST_URL;
import static org.hamcrest.MatcherAssert.assertThat;

public class FormulaOneTests {

    protected static F1Steps f1Steps;

    @BeforeTest
    public void setUp() {
        f1Steps = new F1Steps();
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    public void f1Test() {
        Response response = f1Steps.getF1Drivers(ERGAST_URL);

        DriversItem driver = JsonPath.from(response.getBody().asString()).getObject("MRData.DriverTable.Drivers[0]", DriversItem.class);

        DriversItem expectedDriver = F1DriverBody.createExpectedDriver();

        assertThat(driver.getDriverId(), Matchers.equalTo(expectedDriver.getDriverId()));
        assertThat(driver.getPermanentNumber(), Matchers.equalTo(expectedDriver.getPermanentNumber()));
    }
}
