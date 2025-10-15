package ru.clonsaldafon.spring_warranty_cards.dto.home_appliance;

import jakarta.validation.constraints.Size;

public class UpdateHomeApplianceDto {

    @Size(max = 50)
    private String title;

    @Size(max = 100)
    private String serialNumber;

    private Long userId;
    private Long warrantyCardId;

    public UpdateHomeApplianceDto(String title, String serialNumber, Long userId, Long warrantyCardId) {
        this.title = title;
        this.serialNumber = serialNumber;
        this.userId = userId;
        this.warrantyCardId = warrantyCardId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWarrantyCardId() {
        return warrantyCardId;
    }

    public void setWarrantyCardId(Long warrantyCardId) {
        this.warrantyCardId = warrantyCardId;
    }
}
