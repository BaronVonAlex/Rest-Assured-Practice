package ge.tbcacad.openapi.petstore3;

import ge.tbcacad.steps.openapi.petstore3openapi.Petstore3OpenApiSteps;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pet.store.v3.invoker.ApiClient;
import pet.store.v3.invoker.JacksonObjectMapper;
import pet.store.v3.model.Order;
import pet.store.v3.model.Pet;

import static ge.tbcacad.data.constants.Constants.*;
import static io.restassured.RestAssured.config;

public class Petstore3SwaggerOpenAPITests {

    protected static Petstore3OpenApiSteps petstore3OpenApiSteps;
    private ApiClient api;

    @BeforeSuite
    public void createApiClient() {
        petstore3OpenApiSteps = new Petstore3OpenApiSteps();
        api = ApiClient.api(ApiClient.Config.apiConfig()
                .reqSpecSupplier(() -> new RequestSpecBuilder()
                        .log(LogDetail.ALL)
                        .setConfig(config()
                                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                                        .defaultObjectMapper(JacksonObjectMapper.jackson())))
                        .addFilter(new ErrorLoggingFilter())
                        .setBaseUri(PETSTORE_BASE_URI)));
    }

    @Test(description = "Create Order Body and then make Place Order Request, get Response and validate received information.")
    public void orderRequestTest() {
        Order newOrder = petstore3OpenApiSteps
                .createOrderBody(12L, 55L, Order.StatusEnum.PLACED, Boolean.TRUE, 5);

        Order response = petstore3OpenApiSteps.getPlacedOderResponse(api, newOrder);

        petstore3OpenApiSteps
                .validateOrderId(response, 12L)
                .validateOrderStatus(response, Order.StatusEnum.PLACED);
    }

    @Test(description = "Create new Pet body, place New Pet and get response, then make validations.")
    public void petPostRequestTest() {
        Pet newPet = petstore3OpenApiSteps
                .createPet(PET_ID,
                        PET_NAME,
                        CATEGORY_ID,
                        CATEGORY_NAME,
                        TAGS_ID,
                        TAGS_NAME,
                        PHOTO_URLS,
                        Pet.StatusEnum.AVAILABLE);

        Pet newPetResponse = petstore3OpenApiSteps.getNewPetResponse(api, newPet);

        petstore3OpenApiSteps
                .validatePetName(newPetResponse, PET_NAME)
                .validatePetId(newPetResponse, PET_ID);
    }
}
