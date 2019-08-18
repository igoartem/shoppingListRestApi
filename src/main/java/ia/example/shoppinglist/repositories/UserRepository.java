package ia.example.shoppinglist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ia.example.shoppinglist.domain.Product;
import ia.example.shoppinglist.domain.User;
@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, String> {
}
