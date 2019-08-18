package ia.example.shoppinglist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ia.example.shoppinglist.domain.Product;
@Repository("productRepository")
public interface ProductRepository extends CrudRepository<Product, String> {
}
