package com.devencarnacion.budget.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.devencarnacion.budget.dto.user.UserCreateRequestDTO;
import com.devencarnacion.budget.dto.user.UserResponseDTO;
import com.devencarnacion.budget.dto.user.UserUpdateRequestDTO;
import com.devencarnacion.budget.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Entity -> Response
    UserResponseDTO toResponse(User user);

    // Create DTO -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserCreateRequestDTO dto);

    // Update parcial (IGNORA nulls)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(UserUpdateRequestDTO dto, @MappingTarget User entity);
}
