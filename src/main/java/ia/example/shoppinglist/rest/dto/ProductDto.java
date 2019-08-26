package ia.example.shoppinglist.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProductDto extends EntityDto {
    private String name;
    private String description;
    private String price;
    private CategoryDto category;
    private ManufacturerDto manufacturer;
}
