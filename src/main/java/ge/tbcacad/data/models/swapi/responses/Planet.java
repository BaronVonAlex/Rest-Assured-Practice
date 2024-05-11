package ge.tbcacad.data.models.swapi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planet {
    private String next;
    private Object previous;
    private int count;
    private List<ResultsItem> results;
}