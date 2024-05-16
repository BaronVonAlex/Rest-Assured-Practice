package ge.tbcacad.countryinfoservice;

import ge.tbcacad.steps.countryinfoservice.CountryInfoServiceSteps;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


@Epic("Country Info Services")
@Feature("Continents Information")
public class CountryInfoServiceTests {

    protected static CountryInfoServiceSteps countryInfoServiceSteps;

    @BeforeTest(description = "Set up RestAssured filters (AllureRestAssured) and initialize steps class")
    public void setUp() {
        RestAssured.filters(new AllureRestAssured());
        countryInfoServiceSteps = new CountryInfoServiceSteps();
    }

    @Test(description = "Validate various aspects of the ListOfContinentsByName service")
    @Story("Service Response Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Service Documentation", url = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso")
    public void countryInfoServiceValidations() {
        countryInfoServiceSteps
                .getListOfContinentsByName()
                .countValidation(6)
                .nodeValuesValidation()
                .validateIfsNameHasProperValue()
                .validateContinentNodeValues()
                .checkIfEachSNameIsUnique()
                .codeAndNameValidation()
                .validateIfAlphabetical()
                .validateIfAll6ContinentsArePresent()
                .checkIfNameDoNotContainDigits()
                .findSCodeOandValidateIfOceania()
                .findAllSNamesStartingWithAAndEndingWithCa();
    }
}
