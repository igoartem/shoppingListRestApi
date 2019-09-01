package ia.example.shoppinglist.app;

import ia.example.shoppinglist.rest.service.UniversalMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UniversalMapperTest {

    //параметризированный тест на все сущности

    @Autowired
    private UniversalMapper universalMapper;

    @Test
    public void toEntityTest(){

    }

    @Test
    public void toDtoTest(){

    }


}
