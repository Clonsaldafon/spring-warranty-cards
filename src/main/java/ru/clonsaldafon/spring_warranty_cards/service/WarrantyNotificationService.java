package ru.clonsaldafon.spring_warranty_cards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.clonsaldafon.spring_warranty_cards.model.WarrantyCard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class WarrantyNotificationService {

    @Autowired
    private WarrantyCardService warrantyCardService;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 56 14 * * *")
    public void sendWarrantyReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime in30Days = now.plusDays(30);

        List<WarrantyCard> cards = warrantyCardService.findExpiringIn30Days(in30Days);

        for (WarrantyCard card : cards) {
            if (card.getNotified() != true) {
                String email = card.getHomeAppliance().getUser().getEmail();
                String title = card.getHomeAppliance().getTitle();
                String endedAt = formatDateTime(card.getEndedAt());

                emailService.sendReminderEmail(email, title, endedAt);
                card.setNotified(true);
                warrantyCardService.save(card);
            }
        }
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        return dateTime.format(formatter);
    }
}
