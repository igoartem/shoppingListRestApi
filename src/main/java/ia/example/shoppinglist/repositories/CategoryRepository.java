package ia.example.shoppinglist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ia.example.shoppinglist.domain.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends CrudRepository<Category, String> {
}
