package ge.tbcacad.data.models.petstore3swagger.responses;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "PetResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class PetResponse {

    private long id;
    private String name;
    private Category category;
    private String[] photoUrls;
    private Tag[] tags;
    private String status;
}
