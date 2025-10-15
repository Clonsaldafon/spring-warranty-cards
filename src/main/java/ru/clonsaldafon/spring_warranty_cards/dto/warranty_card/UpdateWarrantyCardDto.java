package ru.clonsaldafon.spring_warranty_cards.dto.warranty_card;

import java.time.LocalDateTime;

public class UpdateWarrantyCardDto {

    private LocalDateTime endedAt;
    private Long serviceCenterId;

    public UpdateWarrantyCardDto(LocalDateTime endedAt, Long serviceCenterId) {
        this.endedAt = endedAt;
        this.serviceCenterId = serviceCenterId;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public Long getServiceCenterId() {
        return serviceCenterId;
    }

    public void setServiceCenterId(Long serviceCenterId) {
        this.serviceCenterId = serviceCenterId;
    }
}
