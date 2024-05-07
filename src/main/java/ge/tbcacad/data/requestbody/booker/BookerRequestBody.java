package ge.tbcacad.data.requestbody.booker;

import com.github.javafaker.Faker;
import org.json.JSONObject;

public class BookerRequestBody {
    public static JSONObject bookerRequestBody() {
        Faker faker = new Faker();

        return new JSONObject()
                .put("firstname", "Alex")
                .put("lastname", "Baron")
                .put("totalprice", faker.number().randomNumber())
                .put("depositpaid", true)
                .put("bookingdates", new JSONObject()
                        .put("checkin", "2024-03-24")
                        .put("checkout", "2024-04-23")
                )
                .put("additionalneeds", "Pole Position");
    }
}
