package ru.clonsaldafon.spring_warranty_cards.dto.warranty_card;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WarrantyCardViewDto {

    private Long id;
    private Long homeApplianceId;
    private String homeApplianceTitle;
    private String homeApplianceSerialNumber;
    private String startedAt;
    private String endedAt;
    private String serviceCenterTitle;

    public WarrantyCardViewDto(Long id, Long homeApplianceId, String homeApplianceTitle,
                               String homeApplianceSerialNumber, LocalDateTime startedAt, LocalDateTime endedAt,
                               String serviceCenterTitle) {
        this.id = id;
        this.homeApplianceId = homeApplianceId;
        this.homeApplianceTitle = homeApplianceTitle;
        this.homeApplianceSerialNumber = homeApplianceSerialNumber;
        this.startedAt = formatDateTime(startedAt);
        this.endedAt = formatDateTime(endedAt);
        this.serviceCenterTitle = serviceCenterTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHomeApplianceId() {
        return homeApplianceId;
    }

    public void setHomeApplianceId(Long homeApplianceId) {
        this.homeApplianceId = homeApplianceId;
    }

    public String getHomeApplianceTitle() {
        return homeApplianceTitle;
    }

    public void setHomeApplianceTitle(String homeApplianceTitle) {
        this.homeApplianceTitle = homeApplianceTitle;
    }

    public String getHomeApplianceSerialNumber() {
        return homeApplianceSerialNumber;
    }

    public void setHomeApplianceSerialNumber(String homeApplianceSerialNumber) {
        this.homeApplianceSerialNumber = homeApplianceSerialNumber;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(String endedAt) {
        this.endedAt = endedAt;
    }

    public String getServiceCenterTitle() {
        return serviceCenterTitle;
    }

    public void setServiceCenterTitle(String serviceCenterTitle) {
        this.serviceCenterTitle = serviceCenterTitle;
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        return dateTime.format(formatter);
    }
}
