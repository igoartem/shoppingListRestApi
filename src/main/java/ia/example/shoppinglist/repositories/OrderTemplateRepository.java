package ia.example.shoppinglist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ia.example.shoppinglist.domain.Order;

/**
 * Репозиторий для шаблонов списков
 */
@Repository("orderTemplateRepository")
public interface OrderTemplateRepository extends CrudRepository<Order, String> {
}
