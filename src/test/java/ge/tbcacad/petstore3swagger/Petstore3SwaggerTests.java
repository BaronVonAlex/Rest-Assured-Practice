package ge.tbcacad.petstore3swagger;

import com.fasterxml.jackson.core.JsonProcessingException;
import ge.tbcacad.data.models.petstore3swagger.responses.OrderResponse;
import ge.tbcacad.data.models.petstore3swagger.responses.PetResponse;
import ge.tbcacad.data.requestbody.petstore.PetPostRequestBody;
import ge.tbcacad.steps.petstore3swagger.Petstore3Steps;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Petstore3SwaggerTests {
    protected static Petstore3Steps petstore3Steps;

    @BeforeTest
    public void setUp() {
        petstore3Steps = new Petstore3Steps();
        RestAssured.filters(new AllureRestAssured());
    }

    @Test(description = "Create Post request with Lombok Generated body, Deserialize with same thing and make few assertions.")
    public void createPostRequest() {
        Response response = petstore3Steps.createOrderPostOrder();
        OrderResponse deserializedResponse = response.as(OrderResponse.class);

        assertThat(deserializedResponse.getId(), Matchers.is(ORDER_ID_EXP_VALUE));
        assertThat(deserializedResponse.getShipDate(), Matchers.hasToString(ODER_DATE_EXP_VALUE));
        assertThat(deserializedResponse.getQuantity(), Matchers.is(ORDER_QUANTITIY_EXP_VALUE));
    }

    @Test(description = "Create Post request to add body, with XML Ser/Deser. Make couple validations after getting response.")
    public void postPetRequest() throws JsonProcessingException {
        String xmlBody = PetPostRequestBody.postPetRequestBody();

        Response response = petstore3Steps.createPostRequest(xmlBody);
        PetResponse petResponse = response.as(PetResponse.class);

        assertThat(petResponse.getName(), Matchers.hasToString(EXP_PET_NAME));
        assertThat(petResponse.getId(), Matchers.is(EXP_PET_ID));
        assertThat(petResponse.getStatus(), Matchers.hasToString(EXP_PET_STATUS));
    }
}
