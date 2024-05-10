package ge.tbcacad.steps.petstore3swagger;

import ge.tbcacad.data.models.petstore3swagger.requests.NewOrder;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Petstore3Steps {
    @Step("Create Request body and make Post request (Order)")
    public Response createOrderPostOrder(){
        NewOrder orderBody = NewOrder.builder()
                .id(8123L)
                .petId(1231246L)
                .quantity(7)
                .shipDate(LocalDateTime.parse("2021-05-10T15:04:37.534"))
                .status("approved")
                .complete(true)
                .build();

        return RestAssured.given()
                .baseUri("https://petstore3.swagger.io/api/v3")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(orderBody)
                .when()
                .post("/store/order")
                .then()
                .extract()
                .response();
    }
}
