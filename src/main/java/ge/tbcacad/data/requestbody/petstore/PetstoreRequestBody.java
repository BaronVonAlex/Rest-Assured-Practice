package ge.tbcacad.data.requestbody.petstore;

import com.github.javafaker.Faker;
import org.json.JSONArray;
import org.json.JSONObject;

import static ge.tbcacad.data.constants.Constants.*;

public class PetstoreRequestBody {
    static Faker faker = new Faker();
    public static int id = faker.number().numberBetween(1, 10000);

    public static JSONObject returnPetstoreReqBody() {
        JSONArray photoUrls = new JSONArray();
        photoUrls.put(faker.internet().url());

        JSONArray tags = new JSONArray();
        JSONObject tag = new JSONObject()
                .put(ID, id)
                .put(PET_NAME, "Veigar");
        tags.put(tag);

        return new JSONObject()
                .put(ID, id)
                .put(CATEGORY, new JSONObject()
                        .put(ID, id)
                        .put(PET_NAME, "Baron"))
                .put(PET_NAME, "Alex")
                .put(PHOTO_URLS, photoUrls)
                .put(TAGS, tags)
                .put(STATUS, "available");
    }

    public static JSONObject updatedPetReqBody() {
        JSONArray photoUrls = new JSONArray();
        photoUrls.put(faker.internet().url());

        JSONArray tags = new JSONArray();
        JSONObject tag = new JSONObject()
                .put(ID, id)
                .put(PET_NAME, "Veigar");
        tags.put(tag);

        return new JSONObject()
                .put(ID, id)
                .put(CATEGORY, new JSONObject()
                        .put(ID, id)
                        .put(PET_NAME, "Jason"))
                .put(PET_NAME, "Alex")
                .put(PHOTO_URLS, photoUrls)
                .put(TAGS, tags)
                .put(STATUS, "sold");
    }
}
