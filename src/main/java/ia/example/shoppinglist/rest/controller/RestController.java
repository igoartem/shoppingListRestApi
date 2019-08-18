package ia.example.shoppinglist.rest.controller;

import org.springframework.data.repository.CrudRepository;

import com.sun.xml.internal.bind.v2.model.core.ID;

import ia.example.shoppinglist.domain.Entity;

public abstract class RestController<T extends Entity> {

    private CrudRepository<T, String> repository;

    public RestController(CrudRepository<T, String> repository) {
        this.repository = repository;
    }
}
