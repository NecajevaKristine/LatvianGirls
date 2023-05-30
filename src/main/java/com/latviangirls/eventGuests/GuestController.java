package com.latviangirls.eventGuests;

import com.latviangirls.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController (GuestService guestService){
        this.guestService = guestService;
    }

    @GetMapping("/WelcomeToSeeInvitation")
    public String displayInvitationPage(){
        return "invitation";
    }

    @PostMapping("/invitation")
    public String letGuestSeeInvitation(InvitationOpeningRequest invitationOpeningRequest){
        try {
            Guest loggedInGuest = this.guestService.verifyGuest(invitationOpeningRequest.guestEmail, invitationOpeningRequest.guestProjectCode);
            return "redirect:WelcomeToSeeInvitation";
        }catch (Exception exception){
            return "redirect:entry?status=LOGIN_FAILED&message=Login failed"+ exception.getMessage();
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
            return "redirect:profile?status=REGISTER_FAILED&message=Registration failed";
        }
    }


}
