package ru.clonsaldafon.spring_warranty_cards.dto.home_appliance;

public class HomeApplianceDto {

    private Long id;
    private String title;
    private String serialNumber;
    private Long userId;
    private Long warrantyCardId;

    public HomeApplianceDto(Long id, String title, String serialNumber, Long userId, Long warrantyCardId) {
        this.id = id;
        this.title = title;
        this.serialNumber = serialNumber;
        this.userId = userId;
        this.warrantyCardId = warrantyCardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
