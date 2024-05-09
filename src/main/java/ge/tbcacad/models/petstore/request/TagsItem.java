package ge.tbcacad.models.petstore.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TagsItem {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "TagsItem{" +
                        "name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}