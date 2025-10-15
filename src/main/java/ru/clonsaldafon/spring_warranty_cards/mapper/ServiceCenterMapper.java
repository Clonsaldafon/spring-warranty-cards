package ru.clonsaldafon.spring_warranty_cards.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.clonsaldafon.spring_warranty_cards.dto.service_center.CreateServiceCenterDto;
import ru.clonsaldafon.spring_warranty_cards.dto.service_center.ServiceCenterDto;
import ru.clonsaldafon.spring_warranty_cards.model.ServiceCenter;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ServiceCenterMapper {

    ServiceCenterDto toDto(ServiceCenter serviceCenter);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "warrantyCards", ignore = true)
    ServiceCenter toEntity(CreateServiceCenterDto createServiceCenterDto);
}
