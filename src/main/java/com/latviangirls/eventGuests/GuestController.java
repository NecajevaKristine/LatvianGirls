package com.latviangirls.eventGuests;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class GuestController {
    private GuestServiceImpl guestServiceImpl;

    @Autowired
    public GuestController(GuestServiceImpl guestServiceImpl) {
        super();
        this.guestServiceImpl = guestServiceImpl;
    }

    //handler method to handle list guests and return mode ad view
    @GetMapping("/profile")
    public String viewGuestList(@CookieValue(value = "loggedInUserId") String userId, Model model) {
        try {
            if (userId.isEmpty()) throw new RuntimeException("User session expired, please login to try again");
            model.addAttribute("guestList", this.guestServiceImpl.getAllGuests()); //izsauc no guestService metodi, kas iedod visu listi ar viesiem
            return "userPage";
        } catch (Exception e) {
            return "redirect:entry?status=LOGIN_FAILED&message=" + e.getMessage();
        }
    }

    @GetMapping("/guest/new")
    public String openGuestRegisterForm(@CookieValue(value = "loggedInUserId") String userId,Model model) {
        try {
            if (userId.isEmpty()) throw new RuntimeException("User session expired, please login to try again");
            Guest guest = new Guest(); //uztaisām objektu, lai no formas saglabātu viesa datus
            model.addAttribute("guest", guest);
            return "guestRegister";
        } catch (Exception e) {
            return "redirect:entry?status=LOGIN_FAILED&message=" + e.getMessage();
        }
    }

    @PostMapping("/createGuest")
    public String processGuestRegistration(@CookieValue(value = "loggedInUserId") String userId, @ModelAttribute("guest") Guest guest) {
        //save guest to data base
        try {
            if (userId.isEmpty()) throw new RuntimeException("User session expired, please login to try again");
            guestServiceImpl.saveGuest(guest);
            return "redirect:/profile";
        }catch (Exception e) {
            return "redirect:entry?status=LOGIN_FAILED&message=" + e.getMessage();
        }
    }


    @GetMapping("/showUpdateForm/{guestEmail}")
    public String showFormForUpdate(@CookieValue(value = "loggedInUserId") String userId, @PathVariable("guestEmail") String guestEmail, Model model) {
        try {
            if (userId.isEmpty()) throw new RuntimeException("User session expired, please login to try again");
            Guest guest = guestServiceImpl.getGuestByGuestEmail(guestEmail);
            model.addAttribute("guest", guest);
            return "update_guestInfo";
        } catch (Exception e) {
            return "redirect:entry?status=LOGIN_FAILED&message=" + e.getMessage();
        }
    }
/*
    @GetMapping("/deleteGuest/{guestId}")
    public void deleteByGuestId(@PathVariable("guestId") String guestId) {
        this.guestService.deleteById(guestId);
    }*/

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Beidzās sadaļa, kas atbild par viesu reģistru

    /*    @GetMapping("/WelcomeToSeeInvitation")
        public String redirectToRegistrationPage() {
                  return "redirect:/entry";
        }*/


    @GetMapping("/WelcomeToSeeInvitation")
    public String displayInvitationPage(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "message", required = false) String message,
            Model model //transfers info to html
    ) {
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "invitation";
    }

    @PostMapping("/WelcomeToSeeInvitation")
    public String letGuestSeeInvitation(InvitationOpeningRequest invitationOpeningRequest, HttpServletResponse response) {
        try {
            Guest loggedInGuest = this.guestServiceImpl.verifyGuest(invitationOpeningRequest.guestEmail, invitationOpeningRequest.guestProjectCode);
            if (loggedInGuest == null)
                throw new RuntimeException("There is not found info about You! Please, contact with invitation sender!");

            Cookie cookie = new Cookie("loggedInGuestEmail", loggedInGuest.getGuestEmail().toString());
            cookie.setMaxAge(3000);
            response.addCookie(cookie);

            return "redirect:WelcomeToSeeInvitation";

        } catch (Exception exception) {
            return "redirect:entry?status=LOGIN_FAILED&message=" + exception.getMessage();
        }
    }
}



//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

/*
    @GetMapping("/guestRegister")
    public String showRegisterPage() {
        return "guestRegister";
    }*/

/*
    @GetMapping("/guest/confirmation")
    public String openGuestConfForm(@PathVariable("guestEmail") {
        Guest guest = new Guest(); //uztaisām objektu, lai no formas saglabātu viesa datus
        model.addAttribute("guest", guest);
        return "confirmationForm";
    }

    @PostMapping("/guestAnswer/{guestEmail}")
    public String processGuestConfirmation(@PathVariable("guestEmail") String guestEmail, Model model, InvitationOpeningRequest invitationOpeningRequest, HttpServletResponse response) {
        model.addAttribute("guest", guestEmail);
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


}
=======
      }*/





/*https://www.youtube.com/watch?v=QwQuro7ekvc&t=2246s*/