/*package com.latviangirls.SendEmail;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootApplication
public class EmailSendController {

    @Autowired
    private EmailSenderService senderService;

    public static void main(String[] args) {
        SpringApplication.run(EmailSendController.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)

    public void triggerMail() throws MessagingException {
        senderService.sendSimpleEmail("guestEmail",
                "subject",
                "body");
    }
}*/

