package ge.tbcacad.steps.f1;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class F1Steps {
    @Step("Return F1 API Response")
    public Response getF1Drivers(String baseUrl) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/api/f1/2016/drivers.json")
                .then()
                .extract().response();
    }
}
