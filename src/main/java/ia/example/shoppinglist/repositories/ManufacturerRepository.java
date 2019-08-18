package ia.example.shoppinglist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ia.example.shoppinglist.domain.Manufacturer;

@Repository("manufacturerRepository")
public interface ManufacturerRepository extends CrudRepository<Manufacturer, String> {
}
