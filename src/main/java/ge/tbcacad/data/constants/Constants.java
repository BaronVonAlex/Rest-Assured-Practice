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
    public static final String bookerBaseUrl = "https://restful-booker.herokuapp.com";
    public static final String bookstoreBaseUrl = "https://bookstore.toolsqa.com";
    public static final String petstoreBaseUrl = "https://petstore.swagger.io";

    public static final String BOOK_ONE_EXP_AUTH = "Richard E. Silverman";
    public static final String BOOK_TWO_EXP_AUTH = "Addy Osmani";

    public static final long bookingId = 12;
    public static final String username = "admin";
    public static final String password = "password123";

    public static final String AVAILABLE_STATUS = "available";
    public static final String CATEGORY_ID = "category.id";
    public static final String CATEGORY_NAME = "category.name";
    public static final String PET_NAME = "name";
    public static final String PHOTO_URLS = "photoUrls";
    public static final String TAGS_ID = "tags.id";
    public static final String TAGS_NAME = "tags.name";
    public static final String STATUS = "status";
    public static final String BOOK_PAGES = "books.pages";
    public static final String ID = "id";
    public static final String CATEGORY = "category";
    public static final String TAGS = "tags";
    public static final String CODE = "code";
    public static final String MESSAGE = "message";

    public static final String UPLOAD_EXP_BYTES = "37886";
    public static final String UPLOAD_EXP_NAME = "Baron";
    public static final String UPLOAD_EXP_FILENAME = "9eb83d4058c144401c5ea2596e658dbf";

    public static final String BOOK_ASRT_MSG_1 = "Book with ID : ";
    public static final String BOOK_ASRT_MSG_2 = " Has page value less than 1000.";
    public static final String BOOKER_EXP_NAME = "Sally";
    public static final String ERGAST_URL = "http://ergast.com";
}
