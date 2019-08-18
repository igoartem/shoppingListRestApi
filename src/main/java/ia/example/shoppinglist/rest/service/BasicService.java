package ia.example.shoppinglist.rest.service;

import java.util.List;

import ia.example.shoppinglist.rest.dto.EntityDto;

public interface BasicService<T extends EntityDto> {

    T save(final T object);
    T findById(final String objectId);
    List<T> findAll();
    T update(String id, T object);
    void delete(final String objectId);

}
