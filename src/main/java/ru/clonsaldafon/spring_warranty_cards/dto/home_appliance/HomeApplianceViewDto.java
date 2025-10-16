package ru.clonsaldafon.spring_warranty_cards.dto.home_appliance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeApplianceViewDto {

    private String title;
    private String serialNumber;
    private String warrantyStartedAt;
    private String warrantyEndedAt;
    private String serviceCenterTitle;

    public HomeApplianceViewDto(String title, String serialNumber, LocalDateTime warrantyStartedAt,
                                LocalDateTime warrantyEndedAt, String serviceCenterTitle) {
        this.title = title;
        this.serialNumber = serialNumber;
        this.warrantyStartedAt = formatDateTime(warrantyStartedAt);
        this.warrantyEndedAt = formatDateTime(warrantyEndedAt);
        this.serviceCenterTitle = serviceCenterTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getWarrantyStartedAt() {
        return warrantyStartedAt;
    }

    public void setWarrantyStartedAt(String warrantyStartedAt) {
        this.warrantyStartedAt = warrantyStartedAt;
    }

    public String getWarrantyEndedAt() {
        return warrantyEndedAt;
    }

    public void setWarrantyEndedAt(String warrantyEndedAt) {
        this.warrantyEndedAt = warrantyEndedAt;
    }

    public String getServiceCenterTitle() {
        return serviceCenterTitle;
    }

    public void setServiceCenterTitle(String serviceCenterTitle) {
        this.serviceCenterTitle = serviceCenterTitle;
    }

    private String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        return dateTime.format(formatter);
    }
}
