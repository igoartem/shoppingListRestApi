package ia.example.shoppinglist.repositories;

import ia.example.shoppinglist.domain.PlannedOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("plannedOrderTemplateRepository")
public interface PlannedOrderRepository extends CrudRepository<PlannedOrder, String> {
}
