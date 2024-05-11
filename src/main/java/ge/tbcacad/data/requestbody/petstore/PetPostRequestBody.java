package ge.tbcacad.data.requestbody.petstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ge.tbcacad.data.models.petstore3swagger.requests.Category;
import ge.tbcacad.data.models.petstore3swagger.requests.Pet;
import ge.tbcacad.data.models.petstore3swagger.requests.Tag;

public class PetPostRequestBody {
    public static String postPetRequestBody() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();

        Pet pet = new Pet();
        pet.setId(4314);
        pet.setName("doggie");
        pet.setStatus("available");
        pet.setPhotoUrls(new String[]{"https://doggie.woof"});

        ge.tbcacad.data.models.petstore3swagger.requests.Category category = new Category();
        category.setId(6546);
        category.setName("string");
        pet.setCategory(category);

        Tag tag = new Tag();
        tag.setId(4564);
        tag.setName("Bomba");
        pet.setTags(new Tag[]{tag});

        return xmlMapper.writeValueAsString(pet);
    }
}
