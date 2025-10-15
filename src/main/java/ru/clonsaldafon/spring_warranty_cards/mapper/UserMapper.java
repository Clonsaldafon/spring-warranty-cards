package ru.clonsaldafon.spring_warranty_cards.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.clonsaldafon.spring_warranty_cards.dto.user.CreateUserDto;
import ru.clonsaldafon.spring_warranty_cards.dto.user.UserDto;
import ru.clonsaldafon.spring_warranty_cards.model.User;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "role", expression = "java(user.getRole().name())")
    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "homeAppliances", ignore = true)
    User toEntity(CreateUserDto createUserDto);
}
