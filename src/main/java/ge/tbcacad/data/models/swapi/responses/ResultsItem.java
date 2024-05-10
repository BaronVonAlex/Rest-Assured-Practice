package ge.tbcacad.data.models.swapi.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;
import java.util.List;

public record ResultsItem(

        @JsonProperty("films")
        List<String> films,

        @JsonProperty("edited")
        String edited,
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        @JsonProperty("created")
        LocalDateTime created,

        @JsonProperty("climate")
        String climate,

        @JsonProperty("rotation_period")
        String rotationPeriod,

        @JsonProperty("url")
        String url,

        @JsonProperty("population")
        String population,

        @JsonProperty("orbital_period")
        String orbitalPeriod,

        @JsonProperty("surface_water")
        String surfaceWater,

        @JsonProperty("diameter")
        String diameter,

        @JsonProperty("gravity")
        String gravity,

        @JsonProperty("name")
        String name,

        @JsonProperty("residents")
        List<String> residents,

        @JsonProperty("terrain")
        String terrain
) {
}