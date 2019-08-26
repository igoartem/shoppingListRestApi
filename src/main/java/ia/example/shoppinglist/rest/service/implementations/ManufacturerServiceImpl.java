package ia.example.shoppinglist.rest.service.implementations;

import ia.example.shoppinglist.domain.Entity;
import ia.example.shoppinglist.domain.Manufacturer;
import ia.example.shoppinglist.repositories.ManufacturerRepository;
import ia.example.shoppinglist.rest.dto.ManufacturerDto;
import ia.example.shoppinglist.rest.service.UniversalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerServiceImpl extends AbstractServiceImpl  {
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerServiceImpl(UniversalMapper universalMapper, ManufacturerRepository manufacturerRepository) {
        super(universalMapper, Manufacturer.class, ManufacturerDto.class);
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public <T extends Entity> CrudRepository<T, String> getCrudRepository() {
        return (CrudRepository<T, String>) manufacturerRepository;
    }
}
