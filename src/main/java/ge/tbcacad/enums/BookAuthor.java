package ge.tbcacad.enums;

public enum BookAuthor {
    BOOK_AUTHOR_ONE("Richard E. Silverman"),
    BOOK_AUTHOR_TWO("Addy Osmani");

    private final String value;

    BookAuthor(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
