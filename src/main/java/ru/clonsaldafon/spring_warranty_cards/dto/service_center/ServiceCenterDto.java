package ru.clonsaldafon.spring_warranty_cards.dto.service_center;

public class ServiceCenterDto {

    private Long id;
    private String title;
    private String address;

    public ServiceCenterDto(Long id, String title, String address) {
        this.id = id;
        this.title = title;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
