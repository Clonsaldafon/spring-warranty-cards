package ru.clonsaldafon.spring_warranty_cards.model;

import jakarta.persistence.*;

@Entity
@Table(name = "home_appliances")
public class HomeAppliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(name = "serial_number", unique = true, length = 100)
    private String serialNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "homeAppliance", cascade = CascadeType.ALL, orphanRemoval = true)
    private WarrantyCard warrantyCard;

    public HomeAppliance(String title, String serialNumber, User user) {
        this.title = title;
        this.serialNumber = serialNumber;
        this.user = user;
    }

    public HomeAppliance() {

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WarrantyCard getWarrantyCard() {
        return warrantyCard;
    }

    public void setWarrantyCard(WarrantyCard warrantyCard) {
        this.warrantyCard = warrantyCard;
    }
}
