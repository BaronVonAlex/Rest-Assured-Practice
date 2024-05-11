package ge.tbcacad.data.requestbody.petstore;

import com.github.javafaker.Faker;
import ge.tbcacad.data.models.petstore.request.AddNewPetRequest;
import ge.tbcacad.data.models.petstore.request.Category;
import ge.tbcacad.data.models.petstore.request.TagsItem;

import java.util.ArrayList;
import java.util.List;

public class PetstoreRequestBody {
    static Faker faker = new Faker();
    public static int id = faker.number().numberBetween(1, 10000);

    public static AddNewPetRequest addAndReturnNewPet() {
        AddNewPetRequest requestBody = new AddNewPetRequest();

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(faker.internet().url());

        Category category = new Category();
        category.setId(id);
        category.setName("Doggie");

        List<TagsItem> tags = new ArrayList<>();
        TagsItem tag = new TagsItem();
        tag.setName("Huskie");
        tag.setId(id);
        tags.add(tag);

        requestBody.setPhotoUrls(photoUrls);
        requestBody.setCategory(category);
        requestBody.setTags(tags);
        requestBody.setName("Rex");
        requestBody.setId(id);
        requestBody.setStatus("available");

        return requestBody;
    }

    public static AddNewPetRequest updatePet() {
        AddNewPetRequest requestBody = new AddNewPetRequest();

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(faker.internet().url());

        Category category = new Category();
        category.setId(id);
        category.setName("Doggie");

        List<TagsItem> tags = new ArrayList<>();
        TagsItem tag = new TagsItem();
        tag.setName("Huskie");
        tag.setId(id);
        tags.add(tag);

        requestBody.setPhotoUrls(photoUrls);
        requestBody.setCategory(category);
        requestBody.setTags(tags);
        requestBody.setName("Mutant");
        requestBody.setId(id);
        requestBody.setStatus("sold");

        return requestBody;
    }
}
