package ru.clonsaldafon.spring_warranty_cards.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "service_centers")
public class ServiceCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 100)
    private String address;

    @OneToMany(mappedBy = "serviceCenter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WarrantyCard> warrantyCards = new HashSet<>();

    public ServiceCenter(String title, String address) {
        this.title = title;
        this.address = address;
    }

    public ServiceCenter() {

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

    public Set<WarrantyCard> getWarrantyCards() {
        return warrantyCards;
    }

    public void setWarrantyCards(Set<WarrantyCard> warrantyCards) {
        this.warrantyCards = warrantyCards;
    }
}
