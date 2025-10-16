package ru.clonsaldafon.spring_warranty_cards.dto.home_appliance;

public class HomeApplianceAdminViewDto {

    private Long id;
    private String title;
    private String serialNumber;
    private Long userId;
    private String userEmail;

    public HomeApplianceAdminViewDto(Long id, String title, String serialNumber, Long userId, String userEmail) {
        this.id = id;
        this.title = title;
        this.serialNumber = serialNumber;
        this.userId = userId;
        this.userEmail = userEmail;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
