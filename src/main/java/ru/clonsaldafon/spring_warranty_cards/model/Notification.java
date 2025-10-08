package ru.clonsaldafon.spring_warranty_cards.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warranty_card_id", nullable = false)
    private WarrantyCard warrantyCard;

    public Notification(LocalDateTime sentAt, WarrantyCard warrantyCard) {
        this.sentAt = sentAt;
        this.warrantyCard = warrantyCard;
    }

    public Notification() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public WarrantyCard getWarrantyCard() {
        return warrantyCard;
    }

    public void setWarrantyCard(WarrantyCard warrantyCard) {
        this.warrantyCard = warrantyCard;
    }
}
