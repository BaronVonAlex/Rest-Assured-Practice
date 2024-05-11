package ge.tbcacad.data.models.petstore3swagger.requests;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "Pet")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pet {

    private long id;
    private String name;
    private Category category;
    private String[] photoUrls;
    private Tag[] tags;
    private String status;
}
