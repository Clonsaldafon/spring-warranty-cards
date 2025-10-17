package ru.clonsaldafon.spring_warranty_cards.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "warranty_cards")
public class WarrantyCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at", nullable = false)
    private LocalDateTime endedAt;

    @Column(name = "notified")
    private Boolean notified = false;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "home_appliance_id", nullable = false)
    private HomeAppliance homeAppliance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_center_id", nullable = false)
    private ServiceCenter serviceCenter;

    public WarrantyCard(LocalDateTime startedAt, LocalDateTime endedAt,
                        HomeAppliance homeAppliance, ServiceCenter serviceCenter) {
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.homeAppliance = homeAppliance;
        this.serviceCenter = serviceCenter;
    }

    public WarrantyCard() {

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

    public Boolean getNotified() {
        return notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
    }

    public HomeAppliance getHomeAppliance() {
        return homeAppliance;
    }

    public void setHomeAppliance(HomeAppliance homeAppliance) {
        this.homeAppliance = homeAppliance;
    }

    public ServiceCenter getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(ServiceCenter serviceCenter) {
        this.serviceCenter = serviceCenter;
    }
}
