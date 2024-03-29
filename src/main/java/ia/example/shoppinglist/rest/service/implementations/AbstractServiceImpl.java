package ia.example.shoppinglist.rest.service.implementations;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.CrudRepository;

import ia.example.shoppinglist.domain.Entity;
import ia.example.shoppinglist.rest.dto.EntityDto;
import ia.example.shoppinglist.rest.service.BasicService;
import ia.example.shoppinglist.rest.service.UniversalMapper;

public abstract class AbstractServiceImpl implements BasicService {
    private static final Logger log = LoggerFactory.getLogger(AbstractServiceImpl.class);
    //    private final CrudRepository<Entity, String> crudRepository;
    protected final UniversalMapper universalMapper;
    @Getter
    protected final Class<? extends Entity> entityClass;
    @Getter
    protected final Class<? extends EntityDto> entityDtoClass;

    public abstract <T extends Entity> CrudRepository<T, String> getCrudRepository();

    public AbstractServiceImpl(UniversalMapper universalMapper, Class<? extends Entity> entityClass, Class<? extends EntityDto> entityDtoClass) {
        //        this.crudRepository = crudRepository;
        this.universalMapper = universalMapper;
        this.entityClass = entityClass;
        this.entityDtoClass = entityDtoClass;
    }

    @Override
    public EntityDto save(EntityDto object) {
        Entity entity = universalMapper.toEntity(object, entityClass);
        Entity save = getCrudRepository().save(entity);
        return universalMapper.toDto(save, entityDtoClass);
    }

    @Override
    public EntityDto findById(String objectId) {
        return universalMapper.toDto(getCrudRepository().findOne(objectId), entityDtoClass);
    }

    @Override
    public List findAll() {
        Iterable<Entity> iterable = getCrudRepository().findAll();
        List<EntityDto> entityDtos = new ArrayList<>();
        iterable.forEach(o -> entityDtos.add(universalMapper.toDto(o, entityDtoClass)));
        return entityDtos;
    }

    @Override
    public EntityDto update(String id, EntityDto object) {
        Entity updateEntity = universalMapper.toEntity(object, entityClass);
        Entity entity = getCrudRepository().findOne(id);

        try {
            BeanUtils.copyProperties(entity, updateEntity);
        } catch (Exception e) {
            log.warn("while copying properties", e);
            //                        throw Throwables.propagate(e);
        }
        Entity updated = getCrudRepository().save(entity);
        return universalMapper.toDto(updated, entityDtoClass);
    }

    @Override
    public void delete(String objectId) {
        getCrudRepository().delete(objectId);
    }
}
