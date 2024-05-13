package ge.tbcacad.openapi.petstore3;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pet.store.v3.invoker.ApiClient;
import pet.store.v3.invoker.JacksonObjectMapper;
import pet.store.v3.model.Category;
import pet.store.v3.model.Order;
import pet.store.v3.model.Pet;
import pet.store.v3.model.Tag;

import java.util.Collections;

import static io.restassured.RestAssured.config;
import static org.hamcrest.MatcherAssert.assertThat;
import static pet.store.v3.invoker.ResponseSpecBuilders.shouldBeCode;
import static pet.store.v3.invoker.ResponseSpecBuilders.validatedWith;

public class Petstore3SwaggerOpenAPITests {

    private ApiClient api;

    @BeforeSuite
    public void createApiClient() {
        api = ApiClient.api(ApiClient.Config.apiConfig()
                .reqSpecSupplier(() -> new RequestSpecBuilder()
                        .log(LogDetail.ALL)
                        .setConfig(config()
                                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                                        .defaultObjectMapper(JacksonObjectMapper.jackson())))
                        .addFilter(new ErrorLoggingFilter())
                        .setBaseUri("https://petstore3.swagger.io/api/v3/")));
    }

    @Test
    public void orderRequestTest() {
        Order newOrder = new Order()
                .id(5L)
                .petId(5L)
                .status(Order.StatusEnum.APPROVED)
                .complete(Boolean.TRUE)
                .quantity(5);

        Order response = api
                .store()
                .placeOrder()
                .body(newOrder)
                .execute(response1 -> {
                    response1.then().log().all();
                    validatedWith(shouldBeCode(200));
                    return response1.as(Order.class);
                });

        assertThat(response.getId(), Matchers.equalTo(5L));
        assertThat(response.getStatus(), Matchers.is(Order.StatusEnum.APPROVED));
    }

    @Test
    public void petPostRequestTest() {
        Pet newPet = new Pet()
                .id(15L)
                .name("Rocky")
                .category(new Category().id(15L).name("Dog"))
                .tags(Collections.singletonList(new Tag().id(15L).name("Wolfie")))
                .addPhotoUrlsItem("https://random.web.link")
                .status(Pet.StatusEnum.AVAILABLE);

        Pet newPetResponse = api
                .pet()
                .addPet()
                .body(newPet)
                .execute(response -> {
                    response.then().log().all();
                    validatedWith(shouldBeCode(200));
                    return response.as(Pet.class);
                });

        assertThat(newPetResponse.getName(), Matchers.hasToString("Rocky"));
        assertThat(newPetResponse.getId(), Matchers.is(15L));
    }
}
