/*package com.latviangirls.SendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/createEmail/{guestEmail}")
    public void sendSimpleEmail(String guestEmail,
                                String subject,
                                String body
    ) {

        {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("kaktina.rita@gmail.com");
            message.setTo(guestEmail);
            message.setText(body);
            message.setSubject(subject);

            mailSender.send(message);
            System.out.println("Mail Sent...");

        }
    }
}*/
