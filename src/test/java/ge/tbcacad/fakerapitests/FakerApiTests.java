package ge.tbcacad.fakerapitests;

import ge.tbcacad.steps.fakerapi.FakerApiSteps;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FakerApiTests {
    protected static FakerApiSteps fakerApiSteps;

    @BeforeTest
    public void setUp() {
        fakerApiSteps = new FakerApiSteps();
        RestAssured.filters(new AllureRestAssured());
    }

    @Test(description = "Create Call to FakerAPI, get response and after that make Schema validation.")
    public void jsonSchemaValidation() {
        Response response = fakerApiSteps.getFakerResponse();
        fakerApiSteps.validateJsonSchema(response);
    }
}
