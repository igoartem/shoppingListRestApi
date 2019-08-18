package ia.example.shoppinglist.app;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import ia.example.shoppinglist.domain.User;
import ia.example.shoppinglist.repositories.CategoryRepository;
import ia.example.shoppinglist.repositories.ManufacturerRepository;
import ia.example.shoppinglist.repositories.ProductRepository;

@RunWith(SpringRunner.class)
@Ignore
public class GenerateData {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Test
    public void generateProduct(){

    }

}
