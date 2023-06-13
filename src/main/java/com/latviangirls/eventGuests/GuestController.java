
        package com.latviangirls.eventGuests;
        import jakarta.servlet.http.Cookie;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.mail.SimpleMailMessage;
        import org.springframework.mail.javamail.JavaMailSender;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.ui.Model;
        import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GuestController {
    private GuestServiceImpl guestServiceImpl;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    HttpServletRequest request;

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

    @GetMapping("/guest/showUpdateForm/{guestEmail}")
    public String showFormForUpdate(@CookieValue(value = "loggedInUserId", required = false) String userId,
                                    @PathVariable String guestEmail,
                                    Model model) {
        try {
            if (userId == null || userId.isEmpty()) throw new RuntimeException("User session expired, please login to try again");

            Guest guest = guestServiceImpl.getGuestByGuestEmail(guestEmail);
            model.addAttribute("guest", guest);

            return "update_guestInfo";
        } catch (Exception e) {
            return "redirect:/entry?status=LOGIN_FAILED&message=" + e.getMessage();
        }
    }

    @PostMapping("/guest/updateGuest/{guestEmail}")
    public String showFormForUpdate(@CookieValue(value = "loggedInUserId") String userId,
                                    @PathVariable String guestEmail,
                                    Model model,
                                    Guest updatedGuest,
                                    RedirectAttributes redirectAttributes) {
        try {
            if (userId.isEmpty()) throw new RuntimeException("User session expired, please login to try again");
            System.out.println(guestEmail);
            Guest newGuest = guestServiceImpl.getGuestByGuestEmail(guestEmail);
            newGuest.setGuestNickName(updatedGuest.getGuestNickName());
            newGuest.setGuestEmail(updatedGuest.getGuestEmail());
            newGuest.setGuestPhoneNumber((updatedGuest.getGuestPhoneNumber()));
//not able to change @
            this.guestServiceImpl.updateGuest(newGuest);

            //        redirectAttributes.addFlashAttribute("message", "Guest info has been updated!");
            return "redirect:/profile#guests";
        } catch (Exception e) {
            return "redirect:/entry?status=UPDATING_FAILED&message=" + e.getMessage();
        }
    }

    @GetMapping("/guest/deleteGuest/{guestId}")
    public String deleteByGuestId(@PathVariable("guestId") Long guestId) {
        this.guestServiceImpl.deleteById(guestId);
        return "redirect:/profile#guests";
    }

    @GetMapping("/WelcomeToSeeInvitation")
    public String displayInvitationPage(
            @CookieValue(value = "loggedInGuestEmail", required = false) String guestEmail,
            @CookieValue(value = "loggedInGuestId", required = false) Long guestId,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "message", required = false) String message,
            Model model //transfers info to html
    ) {
        try {

            Guest guest = this.guestServiceImpl.getGuestById(guestId);
            if (guestEmail == null || guestId == null || guest == null)
                throw new RuntimeException("There is not found info about You! Please, Login!");

            model.addAttribute("guest", guest);
            model.addAttribute("status", status);
            model.addAttribute("message", message);
            return "invitation";
        } catch (Exception exception){
            return "redirect:entry?status=LOGIN_FAILED&message=There is not found info about You! Please, Login!";
        }
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

    @GetMapping("/guest/createEmail/{guestEmail}")
    public String showEmailForm(@CookieValue(value = "loggedInUserId") String userId, @PathVariable("guestEmail") String guestEmail, Model model) {
        try {
            if (userId.isEmpty()) throw new RuntimeException("User session expired, please login to try again");
            Guest guest = guestServiceImpl.getGuestByGuestEmail(guestEmail);
            model.addAttribute("guest", guest);
            return "sendEmailByLink";
        } catch (Exception e) {
            return "redirect:entry?status=SENDING_FAILED&message=" + e.getMessage();
        }
    }

    @PostMapping("/guest/sendEmail/{guestEmail}")
    public String sendInvitationByLink (HttpServletRequest request) {
        String guestEmail = request.getParameter("guestEmail");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kaktina.rita@gmail.com");
        message.setTo(guestEmail);

        String mailSubject = subject;
        String mailBody = body;

        message.setSubject(mailSubject);
        message.setText(mailBody);

        mailSender.send(message);
        return "redirect:/profile#guests";
    }
}