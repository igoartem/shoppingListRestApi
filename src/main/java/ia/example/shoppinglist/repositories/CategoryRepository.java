package ia.example.shoppinglist.repositories;

import org.springframework.data.repository.CrudRepository;

import ia.example.shoppinglist.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, String> {
}
