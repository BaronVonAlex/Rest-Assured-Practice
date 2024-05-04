package ge.tbcacad.data.constants;

public class Constants {
    public static final String JSON_RESPONSE_BODY =
            """
                        {
                          "id": 6,
                          "petId": 76,
                          "quantity": 1,
                          "shipDate": "2024-05-04T08:06:49.817Z",
                          "status": "placed",
                          "complete": "true"
                        }
                    """;

    public static final String STATUS_CODE_ERR_MSG = "Status code is invalid, should be 401 - Unauthorized";
    public static final String ERR_404_MSG = "Status code isn't 404";
}
