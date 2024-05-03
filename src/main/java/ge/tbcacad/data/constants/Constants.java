package ge.tbcacad.data.constants;

public class Constants {
    public static final String JSON_RESPONSE_BODY =
            """
                            {
                              "id": 0,
                              "petId": 0,
                              "quantity": 0,
                              "shipDate": "2024-05-03T10:42:46.645Z",
                              "status": "placed",
                              "complete": true
                            }
                    """;

    public static final String TITLE = "title";
    public static final String ISBN = "isbn";
    public static final String PUB_DATE = "publish_date";
    public static final String PAGES = "pages";

    public static final String TITLE_ERR_MSG = "API response doesn't have title";
    public static final String ISBN_ERR_MSG = "API response doesn't have isbn";
    public static final String PUB_DATE_ERR_MSG = "API response doesn't have publish_date";
    public static final String PAGES_ERR_MSG = "API response doesn't have pages";

    public static final String STATUS_CODE_ERR_MSG = "Status code is invalid, should be 401 - Unauthorized";
    public static final String RESPONSE_ERR_MSG = "Response does not contain appropriate text.";

    public static final String ID_ERR_MSG = "Response does not contain order id";
    public static final String PET_ID_ERR_MSG = "Response does not contain pet id";
    public static final String QUANT_ERR_MSG = "Response does not contain quantity";
    public static final String DATE_ERR_MSG = "Response does not contain ship date";
    public static final String STATUS_ERR_MSG = "Response does not contain status";
    public static final String COMPLETION_STATUS_ERR_MSG = "Response does not contain completion status";

    public static final String CODE_ERR_MSG = "API response doesn't contain Code";
    public static final String TYPE_ERR_MSG = "API response doesn't contain Type";
    public static final String MSG_ERR_MSG = "API response doesn't contain Message";


    public static final String ERR_404_MSG = "Status code isn't 404";
}
