package ru.clonsaldafon.spring_warranty_cards.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.CreateHomeApplianceDto;
import ru.clonsaldafon.spring_warranty_cards.dto.home_appliance.HomeApplianceDto;
import ru.clonsaldafon.spring_warranty_cards.model.HomeAppliance;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HomeApplianceMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "warrantyCardId", source = "warrantyCard.id")
    HomeApplianceDto toDto(HomeAppliance homeAppliance);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "warrantyCard", ignore = true)
    HomeAppliance toEntity(CreateHomeApplianceDto createHomeApplianceDto);
}
