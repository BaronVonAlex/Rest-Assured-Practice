package ge.tbcacad.data.models.petstore3swagger.requests;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"id", "petId", "quantity", "shipDate", "status", "complete"})
public class NewOrder {
    private Long id;
    private Long petId;
    @JsonIgnore
    private Integer quantity;
    @JsonProperty(value = "shipDate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime shipDate;
    private String status;
    private Boolean complete;
}
