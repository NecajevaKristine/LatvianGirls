/*package com.latviangirls.SendEmail;

import com.latviangirls.eventGuests.Guest;
import com.latviangirls.eventGuests.GuestController;
import com.latviangirls.eventGuests.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mail")
public class EmailSendController {


    @Autowired
    public EmailSendController(GuestService guestService,GuestController guestController,EmailService emailService) {
        super();
        this.guestService = guestService;
        this.guestController = guestController;
        this.emailService = emailService;
    }

    @Autowired
    private GuestService guestService;
    private GuestController guestController;
    @Autowired
    private EmailService emailService;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   /* public EmailSendController(EmailService emailService) {
        this.emailService = emailService;
    }
    @PostMapping("/send")
    public String sendEmail(
            @RequestParam(guestService.findByEmail())
            String to, String subject, String body){
        return emailService.sendEmail(file, to, cc, subject, body);
        Guest guest = guestService.getGuestByGuestEmail(guestEmail);
        model.addAttribute("guest", guest);
    }*/

/*
@PostMapping("/send")
public String sendEmail(@RequestParam(value = "file", required = false)MultipartFile[] file, String to, String [] cc, String subject, String body){
        return emailService.sendEmail(file, to, cc, subject, body);
        } */