package ge.tbcacad.data.models.bookstore.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BooksItem {

    @JsonProperty("website")
    private String website;

    @JsonProperty("pages")
    private int pages;

    @JsonProperty("subTitle")
    private String subTitle;

    @JsonProperty("author")
    private String author;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("description")
    private String description;

    @JsonProperty("title")
    private String title;

    @JsonProperty("publish_date")
    private String publishDate;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return
                "BooksItem{" +
                        "website = '" + website + '\'' +
                        ",pages = '" + pages + '\'' +
                        ",subTitle = '" + subTitle + '\'' +
                        ",author = '" + author + '\'' +
                        ",isbn = '" + isbn + '\'' +
                        ",publisher = '" + publisher + '\'' +
                        ",description = '" + description + '\'' +
                        ",title = '" + title + '\'' +
                        ",publish_date = '" + publishDate + '\'' +
                        "}";
    }
}