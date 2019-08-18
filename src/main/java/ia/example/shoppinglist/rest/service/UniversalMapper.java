package ia.example.shoppinglist.rest.service;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ia.example.shoppinglist.domain.Entity;
import ia.example.shoppinglist.rest.dto.EntityDto;

@Component
public class UniversalMapper {
    private final ModelMapper mapper;

    @Autowired
    public UniversalMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public <T extends Entity> Entity toEntity(EntityDto dto, Class<T> entityClass) {
        return Objects.isNull(dto) ? null : mapper.map(dto, entityClass);
    }

    public <T extends EntityDto> EntityDto toDto(Entity entity, Class<T> entityDtoClass) {
        return Objects.isNull(entity) ? null : mapper.map(entity, entityDtoClass);
    }
}
