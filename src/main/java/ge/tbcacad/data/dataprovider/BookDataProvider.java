package ge.tbcacad.data.dataprovider;

import ge.tbcacad.steps.swaggerSteps.SwaggerSteps;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;


public class BookDataProvider {

    protected static SwaggerSteps swaggerSteps;

    @DataProvider(name = "bookDataProvider")
    public Object[][] bookDataProvider() {
        swaggerSteps = new SwaggerSteps();

        ValidatableResponse response = SwaggerSteps.getListOfBooksValidation();

        List<Map<String, Object>> allBooks = SwaggerSteps.extractBooksFromResponse(response);

        Object[][] data = new Object[allBooks.size()][5];
        for (int i = 0; i < allBooks.size(); i++) {
            Map<String, Object> book = allBooks.get(i);
            data[i][0] = i;
            data[i][1] = book.get("title");
            data[i][2] = book.get("isbn");
            data[i][3] = book.get("publish_date");
            data[i][4] = book.get("pages");
        }
        return data;
    }
}
