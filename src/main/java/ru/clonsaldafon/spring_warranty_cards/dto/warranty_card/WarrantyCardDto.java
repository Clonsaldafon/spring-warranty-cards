package ru.clonsaldafon.spring_warranty_cards.dto.warranty_card;

import java.time.LocalDateTime;

public class WarrantyCardDto {

    private Long id;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Long homeApplianceId;
    private Long serviceCenterId;

    public WarrantyCardDto(Long id, LocalDateTime startedAt, LocalDateTime endedAt,
                           Long homeApplianceId, Long serviceCenterId) {
        this.id = id;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.homeApplianceId = homeApplianceId;
        this.serviceCenterId = serviceCenterId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
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
