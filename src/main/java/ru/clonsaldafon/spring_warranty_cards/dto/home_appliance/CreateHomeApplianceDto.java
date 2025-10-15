package ru.clonsaldafon.spring_warranty_cards.dto.home_appliance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateHomeApplianceDto {

    @NotBlank(message = "Title is required")
    @Size(max = 50)
    private String title;

    @NotBlank(message = "Serial number is required")
    @Size(max = 100)
    private String serialNumber;

    public CreateHomeApplianceDto(String title, String serialNumber) {
        this.title = title;
        this.serialNumber = serialNumber;
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
}
