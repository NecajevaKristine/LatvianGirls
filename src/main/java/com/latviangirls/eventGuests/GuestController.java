package com.latviangirls.eventGuests;

import com.latviangirls.users.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController (GuestService guestService){
        this.guestService = guestService;
    }

    @GetMapping("/WelcomeToSeeInvitation")
    public String displayInvitationPage(
            @RequestParam(name="status", required = false) String status,
            @RequestParam(name="message", required = false) String message,
            Model model //transfers info to html
    ){
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "invitation";
    }

    @PostMapping("/WelcomeToSeeInvitation")
    public String letGuestSeeInvitation(InvitationOpeningRequest invitationOpeningRequest, HttpServletResponse response){
        System.out.println(invitationOpeningRequest);
        try {
            Guest loggedInGuest = this.guestService.verifyGuest(invitationOpeningRequest.guestEmail, invitationOpeningRequest.guestProjectCode);
            if(loggedInGuest==null) throw new RuntimeException("There is not found info about You! Please, contact with invitation sender!");

            Cookie cookie = new Cookie("loggedInGuestEmail", loggedInGuest.getGuestEmail().toString());
            cookie.setMaxAge(3000);
            response.addCookie(cookie);
            return "redirect:WelcomeToSeeInvitation";

        }catch (Exception exception){
            return "redirect:entry?status=LOGIN_FAILED&message="+ exception.getMessage();
        }
    }

    @GetMapping("/guestRegister")
    public String showRegisterPage(){
        return "guestRegister";
    }

    @PostMapping("/guestRegister")
    public String processGuestRegistration (Guest guest){
        try{
            this.guestService.createNewGuest(guest);
            return "redirect:profile?status=REGISTER_SUCCESS";
        }catch(Exception exception){
            exception.printStackTrace();
            return "redirect:profile?status=REGISTER_FAILED&message=Registration failed"+exception.getMessage();
        }
    }

    @GetMapping("/guests")
    public String listGuest(Model model){
        model.addAttribute("guests", guestService.getAllGuests()); //izsauc no guestService metodi, kas iedod visu listi ar viesiem
        return "guests";
    }

}
