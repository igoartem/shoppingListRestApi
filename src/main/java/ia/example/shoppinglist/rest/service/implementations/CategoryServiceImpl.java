package ia.example.shoppinglist.rest.service.implementations;

import ia.example.shoppinglist.domain.Category;
import ia.example.shoppinglist.domain.Entity;
import ia.example.shoppinglist.repositories.CategoryRepository;
import ia.example.shoppinglist.rest.dto.CategoryDto;
import ia.example.shoppinglist.rest.service.UniversalMapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends AbstractServiceImpl {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(UniversalMapper universalMapper, CategoryRepository categoryRepository) {
        super(universalMapper, Category.class, CategoryDto.class);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public <T extends Entity> CrudRepository<T, String> getCrudRepository() {
        return (CrudRepository<T, String>) categoryRepository;
    }
}
