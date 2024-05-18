package ge.tbcacad.tempuri;

import ge.tbcacad.steps.tempuri.TempuriSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.tempuri.FindPersonResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.*;
import static org.testng.Assert.assertTrue;


@Epic("Tempuri Service")
@Feature("Tempuri Find User By ID Service")
@Link(name = "WSDL Doc.", url = "https://www.crcind.com/csp/samples/SOAP.Demo.CLS?WSDL")
public class TempuriTests {
    protected static TempuriSteps tempuriSteps;

    @BeforeTest(description = "Initialize R.A. Allure and Tempuri Steps class.")
    public void setUp() {
        RestAssured.filters(new AllureRestAssured());
        tempuriSteps = new TempuriSteps();
    }

    @Step("FindUser by ID Response Validations")
    @Test(description = "Make Request with ID, Marshall Request and Response, make validations.")
    public void findUserById() {
        Response response = tempuriSteps.findPersonById(TEMPURI_USER_ID).findPerson();

        FindPersonResponse findPersonResponse = tempuriSteps.deserializeResponse(response);

        tempuriSteps
                .validateFindPersonResultNotNull(findPersonResponse)
                .validatePersonName(findPersonResponse, TEMPURI_USER_NAME_EXP)
                .validateSSN(findPersonResponse, TEMPURI_USER_SSN_EXP)
                .validateSSNLength(findPersonResponse, TEMPURI_SSN_LENGTH)
                .validateSSNPattern(findPersonResponse)
                .validateDOB(findPersonResponse, TEMPURI_USER_DOB)
                .validateFavoriteColors(findPersonResponse)
                .validatePersonLastName(findPersonResponse, TEMPURI_USER_LASTNAME);

        assertTrue(tempuriSteps.hasRedAsFavoriteColorAtIndexTwo(findPersonResponse));
        assertTrue(tempuriSteps.validateDifferentZipCodes(findPersonResponse));
    }
}
