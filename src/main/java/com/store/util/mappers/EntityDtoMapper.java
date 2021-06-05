package com.store.util.mappers;

import java.util.List;
import java.util.stream.Collectors;

public abstract class EntityDtoMapper<E, D> {

    public abstract  D toDto(E entity);
    public abstract  E toEntity(D dto);

    public   List<D> entityListToDtoList(List<E> entities){
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
    public   List<E> dtoListToEntityList(List<D> dtos){
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
