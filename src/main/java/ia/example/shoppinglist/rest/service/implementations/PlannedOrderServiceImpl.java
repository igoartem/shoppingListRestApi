package ia.example.shoppinglist.rest.service.implementations;

import ia.example.shoppinglist.domain.Entity;
import ia.example.shoppinglist.domain.PlannedOrder;
import ia.example.shoppinglist.repositories.PlannedOrderRepository;
import ia.example.shoppinglist.rest.dto.PlannedOrderDto;
import ia.example.shoppinglist.rest.service.UniversalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PlannedOrderServiceImpl extends AbstractServiceImpl {

    private PlannedOrderRepository plannedOrderRepository;

    @Autowired
    public PlannedOrderServiceImpl(UniversalMapper universalMapper) {
        super(universalMapper, PlannedOrder.class, PlannedOrderDto.class);
    }

    @Override
    public <T extends Entity> CrudRepository<T, String> getCrudRepository() {
        return (CrudRepository<T, String>) plannedOrderRepository;
    }
}
