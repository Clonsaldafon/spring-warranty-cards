package ru.clonsaldafon.spring_warranty_cards.dto.warranty_card;

import jakarta.validation.constraints.NotNull;

public class CreateWarrantyCardDto {

    @NotNull(message = "Home appliance id is required")
    private Long homeApplianceId;

    @NotNull(message = "Service center id is required")
    private Long serviceCenterId;

    public CreateWarrantyCardDto(Long homeApplianceId, Long serviceCenterId) {
        this.homeApplianceId = homeApplianceId;
        this.serviceCenterId = serviceCenterId;
    }

    public Long getHomeApplianceId() {
        return homeApplianceId;
    }

    public void setHomeApplianceId(Long homeApplianceId) {
        this.homeApplianceId = homeApplianceId;
    }

    public Long getServiceCenterId() {
        return serviceCenterId;
    }

    public void setServiceCenterId(Long serviceCenterId) {
        this.serviceCenterId = serviceCenterId;
    }
}
