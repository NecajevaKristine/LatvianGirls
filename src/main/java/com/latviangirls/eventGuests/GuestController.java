package com.latviangirls.eventGuests;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class GuestController {
    private GuestServiceImpl guestServiceImpl;
    @Autowired
    public GuestController(GuestServiceImpl guestServiceImpl) {
        this.guestServiceImpl = guestServiceImpl;
    }
    //handler method to handle list guests and return mode ad view
    @GetMapping("/profile")
    public String viewGuestList(@CookieValue(value = "loggedInUserId") String userId, Model model) {
        try {
            if (userId.isEmpty()) throw new RuntimeException("User session expired, please login to try again");
            model.addAttribute("guestList", this.guestServiceImpl.getAllGuests());
            return "userPage";
        } catch (Exception e) {
            return "redirect:/profile?status=TABLE_NOT_FOUND&message=" + e.getMessage();
        }
    }
    @GetMapping("/guest/new")
    public String openGuestRegisterForm(@CookieValue(value = "loggedInUserId") String userId, Model model) {
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
        } catch (Exception e) {
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
    @GetMapping("/deleteGuest/{guestId}")
    public String deleteByGuestId(@PathVariable("guestId") Long guestId) {
        this.guestServiceImpl.deleteById(guestId);
        return "redirect:/profile";
    }
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
    public String letGuestSeeInvitation(InvitationOpeningRequest invitationOpeningRequest, HttpServletResponse response, Model model) {
        try {
            Guest loggedInGuest = this.guestServiceImpl.verifyGuest(invitationOpeningRequest.guestEmail, invitationOpeningRequest.guestProjectCode);
            if (loggedInGuest == null)
                throw new RuntimeException("There is not found info about You! Please, contact with invitation sender!");
            Cookie cookie = new Cookie("loggedInGuestEmail", loggedInGuest.getGuestEmail().toString());
            Cookie cookie1 = new Cookie("loggedInGuestId", loggedInGuest.getGuestId().toString());
            cookie.setMaxAge(3000);
            cookie1.setMaxAge(3000);
            response.addCookie(cookie);
            response.addCookie(cookie1);
            model.addAttribute("guestId", loggedInGuest.getGuestId());
            return "redirect:WelcomeToSeeInvitation";
        } catch (Exception exception) {
            return "redirect:entry?status=LOGIN_FAILED&message=" + exception.getMessage();
        }
    }
    @GetMapping("/guest/confirmation")
    public String openGuestConfForm(@CookieValue(value = "loggedInGuestEmail") String guestEmail,
                                    @CookieValue(value = "loggedInGuestId") Long guestId, Model model
    ) {
        Guest guest = this.guestServiceImpl.getGuestById(guestId);
        System.out.println(guest);
        model.addAttribute("guest", guest);
        model.addAttribute("guestId", guestId);
        model.addAttribute("guestEmail", guestEmail);
        return "confirmationForm";
    }
    @PostMapping("/guest/guestAnswer")
    public String fillingConfirmationForm(@CookieValue(value = "loggedInGuestEmail") String guestEmail,
                                          @CookieValue(value = "loggedInGuestId") Long guestId,
                                          Model model,
                                          Guest updatedGuest,
                                          RedirectAttributes redirectAttributes) {
        Guest existingGuest = this.guestServiceImpl.getGuestById(guestId);
        existingGuest.setGuestInvitationAcceptance(updatedGuest.getGuestInvitationAcceptance());
        existingGuest.setGuestCount(updatedGuest.getGuestCount());
        existingGuest.setGuestComment(updatedGuest.getGuestComment());
        this.guestServiceImpl.updateGuest(existingGuest);
        redirectAttributes.addFlashAttribute("message", "Your answer has been submitted. Thank you!");
        return "redirect:/WelcomeToSeeInvitation";
    }
    @GetMapping("/entry/")
    public String displayEntryPage(Model model) {
        // Add any necessary model attributes
        model.addAttribute("message", "Your answer has been submitted. Thank you!");
        return "entry";
    }
}