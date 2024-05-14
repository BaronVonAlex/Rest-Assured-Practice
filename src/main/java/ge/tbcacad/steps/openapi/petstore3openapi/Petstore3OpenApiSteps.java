package ge.tbcacad.steps.openapi.petstore3openapi;

import io.qameta.allure.Step;
import org.hamcrest.Matchers;
import pet.store.v3.invoker.ApiClient;
import pet.store.v3.model.Category;
import pet.store.v3.model.Order;
import pet.store.v3.model.Pet;
import pet.store.v3.model.Tag;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static pet.store.v3.invoker.ResponseSpecBuilders.shouldBeCode;
import static pet.store.v3.invoker.ResponseSpecBuilders.validatedWith;

public class Petstore3OpenApiSteps {
    @Step("Create new Order body for Petstore3")
    public Order createOrderBody(Long id, Long petId, Order.StatusEnum statusEnum, Boolean complete, Integer quantity) {
        return new Order()
                .id(id)
                .petId(petId)
                .status(statusEnum)
                .complete(complete)
                .quantity(quantity);
    }

    @Step("Get Response from Petstore3 API PaceOrder() request")
    public Order getPlacedOderResponse(ApiClient api, Order newOrder) {
        return api
                .store()
                .placeOrder()
                .body(newOrder)
                .execute(response1 -> {
                    response1.then().log().all();
                    validatedWith(shouldBeCode(200));
                    return response1.as(Order.class);
                });
    }

    @Step("Validate order ID")
    public Petstore3OpenApiSteps validateOrderId(Order response, Long expectedId) {
        assertThat(response.getId(), Matchers.equalTo(expectedId));
        return this;
    }

    @Step("Validate order status")
    public Petstore3OpenApiSteps validateOrderStatus(Order response, Order.StatusEnum expectedStatus) {
        assertThat(response.getStatus(), Matchers.is(expectedStatus));
        return this;
    }

    @Step("Create order body for new Pet")
    public Pet createPet(Long id, String name, Long categoryId, String categoryName, Long tagId, String tagName, String photoUrl, Pet.StatusEnum status) {
        return new Pet()
                .id(id)
                .name(name)
                .category(new Category().id(categoryId).name(categoryName))
                .tags(Collections.singletonList(new Tag().id(tagId).name(tagName)))
                .addPhotoUrlsItem(photoUrl)
                .status(status);
    }

    @Step("Get response from Petstore3 addPet() request")
    public Pet getNewPetResponse(ApiClient api, Pet newPet) {
        return api
                .pet()
                .addPet()
                .body(newPet)
                .execute(response -> {
                    response.then().log().all();
                    validatedWith(shouldBeCode(200));
                    return response.as(Pet.class);
                });
    }

    @Step("Validate pet name")
    public Petstore3OpenApiSteps validatePetName(Pet response, String expectedName) {
        assertThat(response.getName(), Matchers.hasToString(expectedName));
        return this;
    }

    @Step("Validate pet Id")
    public Petstore3OpenApiSteps validatePetId(Pet response, Long expectedId) {
        assertThat(response.getId(), Matchers.is(expectedId));
        return this;
    }
}
