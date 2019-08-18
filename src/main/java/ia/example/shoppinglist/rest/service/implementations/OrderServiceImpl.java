package ia.example.shoppinglist.rest.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import ia.example.shoppinglist.domain.Entity;
import ia.example.shoppinglist.repositories.OrderRepository;
import ia.example.shoppinglist.rest.dto.EntityDto;
import ia.example.shoppinglist.rest.service.OrderService;
import ia.example.shoppinglist.rest.service.UniversalMapper;

public class OrderServiceImpl extends AbstractServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(CrudRepository<Entity, String> crudRepository, UniversalMapper universalMapper, Class<? extends Entity> entityClass,
            Class<? extends EntityDto> entityDtoClass, OrderRepository orderRepository) {
        super(crudRepository, universalMapper, entityClass, entityDtoClass);
        this.orderRepository = orderRepository;
    }

}
