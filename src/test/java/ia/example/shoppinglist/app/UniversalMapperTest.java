package ia.example.shoppinglist.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ia.example.shoppinglist.app.repositories.TestUtils;
import ia.example.shoppinglist.domain.Category;
import ia.example.shoppinglist.domain.EntryOrder;
import ia.example.shoppinglist.domain.Manufacturer;
import ia.example.shoppinglist.domain.Product;
import ia.example.shoppinglist.rest.dto.CategoryDto;
import ia.example.shoppinglist.rest.dto.EntryOrderDto;
import ia.example.shoppinglist.rest.dto.ManufacturerDto;
import ia.example.shoppinglist.rest.dto.ProductDto;
import ia.example.shoppinglist.rest.service.UniversalMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfigUniversalMapper.class)
public class UniversalMapperTest {

    @Autowired
    private UniversalMapper universalMapper;

    @Test
    public void toEntityTest() {
        CategoryDto categoryDto = TestUtils.createCategoryDto("test");
        Category category = (Category) universalMapper.toEntity(categoryDto, Category.class);
        assertNotNull(category);
        assertEquals(category.getName(), categoryDto.getName());

        ManufacturerDto manufacturerDto = TestUtils.createManufacturerDto("dto name");
        Manufacturer manufacturer = (Manufacturer) universalMapper.toEntity(manufacturerDto, Manufacturer.class);
        assertNotNull(manufacturer);
        assertEquals(manufacturerDto.getName(), manufacturer.getName());

        ProductDto productDto = TestUtils.createProductDto("product");
        Product product = (Product) universalMapper.toEntity(productDto, Product.class);
        assertNotNull(product);
        assertEquals(productDto.getName(), product.getName());
        assertEquals(productDto.getDescription(), product.getDescription());
        assertEquals(productDto.getPrice(), product.getPrice());
    }

    @Test
    public void toDtoTest() {
        Category category = TestUtils.createCategory("test1");
        CategoryDto categoryDto = (CategoryDto) universalMapper.toDto(category, CategoryDto.class);
        assertNotNull(categoryDto);
        assertEquals(category.getName(), categoryDto.getName());

        EntryOrder entryOrder = TestUtils.createEntryOrder(EntryOrder.StateEntryOrder.NEW, "desc", "name");
        EntryOrderDto entryOrderDto = (EntryOrderDto) universalMapper.toDto(entryOrder, EntryOrderDto.class);
        assertNotNull(entryOrderDto);
        assertEquals(entryOrder.getDescription(), entryOrderDto.getDescription());
        assertEquals(entryOrder.getPrice(), entryOrderDto.getPrice());
        assertEquals(entryOrder.getStateOrder(), entryOrderDto.getStateOrder());

        Manufacturer manufacturer = TestUtils.createManufacturer("name");
        ManufacturerDto manufacturerDto = (ManufacturerDto) universalMapper.toDto(manufacturer, ManufacturerDto.class);
        assertNotNull(manufacturerDto);
        assertEquals(manufacturer.getName(), manufacturerDto.getName());
    }

}
