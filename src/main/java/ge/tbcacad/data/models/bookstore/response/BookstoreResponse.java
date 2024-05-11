package ge.tbcacad.data.models.bookstore.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BookstoreResponse {

    @JsonProperty("books")
    private List<BooksItem> books;

    public List<BooksItem> getBooks() {
        return books;
    }

    public void setBooks(List<BooksItem> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return
                "BookstoreResponse{" +
                        "books = '" + books + '\'' +
                        "}";
    }
}