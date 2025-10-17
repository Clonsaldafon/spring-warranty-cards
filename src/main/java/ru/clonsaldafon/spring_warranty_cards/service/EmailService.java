package ru.clonsaldafon.spring_warranty_cards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendReminderEmail(String toEmail, String homeApplianceTitle, String endedAt) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("a.bezmelnitsin.a@yandex.ru");
            message.setTo(toEmail);
            message.setSubject("Напоминание о завершении гарантии");
            message.setText("Здравствуйте!\n\nГарантия на ваше устройство \""
                    + homeApplianceTitle
                    + "\" закончится " + endedAt);
            mailSender.send(message);
        } catch (MailException e) {
            // TODO: logging
        }
    }
}
