package ia.example.shoppinglist.rest.controller;

import ia.example.shoppinglist.rest.service.implementations.CategoryServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/category")
public class CategoryController extends RestController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        super(categoryService);
        this.categoryService = categoryService;
    }
}
