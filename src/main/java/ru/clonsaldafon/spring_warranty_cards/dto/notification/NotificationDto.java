package ru.clonsaldafon.spring_warranty_cards.dto.notification;

import java.time.LocalDateTime;

public class NotificationDto {

    private Long id;
    private LocalDateTime sentAt;
    private Long warrantyCardId;

    public NotificationDto(Long id, LocalDateTime sentAt, Long warrantyCardId) {
        this.id = id;
        this.sentAt = sentAt;
        this.warrantyCardId = warrantyCardId;
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

    public Long getWarrantyCardId() {
        return warrantyCardId;
    }

    public void setWarrantyCardId(Long warrantyCardId) {
        this.warrantyCardId = warrantyCardId;
    }
}
