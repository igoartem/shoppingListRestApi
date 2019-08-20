package ia.example.shoppinglist.app.main;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import ia.example.shoppinglist.domain.Category;
import ia.example.shoppinglist.repositories.CategoryRepository;

@SpringBootApplication
@ComponentScan(basePackages = "ia.example.shoppinglist.app.config")
public class ShoppingListApplication {

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(ShoppingListApplication.class, args);
	}

	@PostConstruct
	public void post(){
		categoryRepository.save(new Category());
	}

}
