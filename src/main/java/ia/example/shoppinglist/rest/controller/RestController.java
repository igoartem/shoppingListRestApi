package ia.example.shoppinglist.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ia.example.shoppinglist.rest.dto.EntityDto;
import ia.example.shoppinglist.rest.service.BasicService;

public abstract class RestController<T extends EntityDto> {
    private static final Logger log = LoggerFactory.getLogger(RestController.class);

    private final BasicService basicService;

    public RestController(BasicService basicService) {
        this.basicService = basicService;
    }

    @RequestMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<T> listAll() {
        Iterable<T> all = this.basicService.findAll();
        List<T> entities = new ArrayList<>();
        all.forEach(entities::add);
        return entities;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void create(@RequestBody T object) {
        log.debug("Сreate() with body {} of type {}", object, object.getClass());
        basicService.save(object);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    T get(@PathVariable String id) {
        return (T) basicService.findById(id);
    }

    @PostMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void update(@PathVariable String id, @RequestBody T object) {
        log.debug("update() of id#{} with body {}", id, object);
        log.debug("T json is of type {}", object.getClass());
        EntityDto updated = basicService.update(id, object);
        log.debug("updated enitity: {}", updated);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable String id) {
        basicService.delete(id);

    }
}
