package ge.tbcacad.data.models.swapi.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Planet(

        @JsonProperty("next")
        String next,

        @JsonProperty("previous")
        Object previous,

        @JsonProperty("count")
        int count,

        @JsonProperty("results")
        List<ResultsItem> results
) {
}