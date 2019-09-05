package ia.example.shoppinglist.repositories;

import ia.example.shoppinglist.domain.PlannedOrder;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("plannedOrderTemplateRepository")
public interface PlannedOrderRepository extends CrudRepository<PlannedOrder, String> {
    @Query("{'userId' : ?0}")
    List<PlannedOrder> findPlannedOrderByUserId(String userId);

    @Query("{'planDate':{$exists:true}}")
    List<PlannedOrder> findPlannedOrdersByPlanCronIsNotNull();

}
