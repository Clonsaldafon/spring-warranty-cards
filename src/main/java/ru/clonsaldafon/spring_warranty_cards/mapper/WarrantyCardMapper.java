package ru.clonsaldafon.spring_warranty_cards.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.CreateWarrantyCardDto;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.UpdateWarrantyCardDto;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.WarrantyCardDto;
import ru.clonsaldafon.spring_warranty_cards.dto.warranty_card.WarrantyCardViewDto;
import ru.clonsaldafon.spring_warranty_cards.model.WarrantyCard;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WarrantyCardMapper {

    @Mapping(target = "homeApplianceId", source = "homeAppliance.id")
    @Mapping(target = "serviceCenterId", source = "serviceCenter.id")
    WarrantyCardDto toDto(WarrantyCard warrantyCard);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "homeAppliance", ignore = true)
    @Mapping(target = "serviceCenter", ignore = true)
    WarrantyCard toEntity(CreateWarrantyCardDto createWarrantyCardDto);

    @Mapping(target = "homeAppliance", ignore = true)
    @Mapping(target = "serviceCenter", ignore = true)
    void updateEntityFromDto(UpdateWarrantyCardDto updateWarrantyCardDto, @MappingTarget WarrantyCard warrantyCard);

    @Mapping(target = "homeApplianceId", source = "homeAppliance.id")
    @Mapping(target = "homeApplianceTitle", source = "homeAppliance.title")
    @Mapping(target = "homeApplianceSerialNumber", source = "homeAppliance.serialNumber")
    @Mapping(target = "serviceCenterTitle", source = "serviceCenter.title")
    WarrantyCardViewDto toViewDto(WarrantyCard warrantyCard);
}
