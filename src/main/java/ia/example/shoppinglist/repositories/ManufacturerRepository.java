package ia.example.shoppinglist.repositories;

import org.springframework.data.repository.CrudRepository;

import ia.example.shoppinglist.domain.Manufacturer;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, String> {
}
