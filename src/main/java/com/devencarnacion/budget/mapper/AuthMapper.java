package com.devencarnacion.budget.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.devencarnacion.budget.dto.auth.RegisterRequest;
import com.devencarnacion.budget.model.User;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    // Register DTO -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(RegisterRequest dto);  

}
