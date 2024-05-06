package ge.tbcacad.data.requestbody.petstore;

import com.github.javafaker.Faker;
import org.json.JSONArray;
import org.json.JSONObject;

public class PetstoreRequestBody {
    static Faker faker = new Faker();
    public static int id = faker.number().numberBetween(1, 10000);

    public static JSONObject returnPetstoreReqBody() {
        JSONArray photoUrls = new JSONArray();
        photoUrls.put(faker.internet().url());

        JSONArray tags = new JSONArray();
        JSONObject tag = new JSONObject()
                .put("id", id)
                .put("name", "Veigar");
        tags.put(tag);

        return new JSONObject()
                .put("id", id)
                .put("category", new JSONObject()
                        .put("id", id)
                        .put("name", "Baron"))
                .put("name", "Alex")
                .put("photoUrls", photoUrls)
                .put("tags", tags)
                .put("status", "available");
    }

    public static JSONObject updatedPetReqBody() {
        JSONArray photoUrls = new JSONArray();
        photoUrls.put(faker.internet().url());

        JSONArray tags = new JSONArray();
        JSONObject tag = new JSONObject()
                .put("id", id)
                .put("name", "Veigar");
        tags.put(tag);

        return new JSONObject()
                .put("id", id)
                .put("category", new JSONObject()
                        .put("id", id)
                        .put("name", "Jason"))
                .put("name", "Alex")
                .put("photoUrls", photoUrls)
                .put("tags", tags)
                .put("status", "sold");
    }
}
