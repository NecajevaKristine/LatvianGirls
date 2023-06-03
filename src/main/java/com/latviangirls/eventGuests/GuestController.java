package com.latviangirls.eventGuests;

import com.latviangirls.users.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class GuestController {


    @Autowired
    public GuestController(GuestService guestService) {
        super();
        this.guestService = guestService;
    }
    //Zemāk esošos izmantosim guest reģistram un tabulai
    @Autowired
    private GuestService guestService;


    //handler method to handle list guests and return mode ad view
    @GetMapping("/")
    public String viewGuestList(Model model) {
        model.addAttribute("guestList", guestService.getAllGuests()); //izsauc no guestService metodi, kas iedod visu listi ar viesiem
        return "userPage";
    }

    @GetMapping("/guest/new")
    public String openGuestRegisterForm(Model model) {
        Guest guest = new Guest(); //uztaisām objektu, lai no formas saglabātu viesa datus
        model.addAttribute("guest", guest);
        return "guestRegister";
    }
    @PostMapping("/createGuest")
    public String  processGuestRegistration(@ModelAttribute("guest") Guest guest){
       //save guest to data base
        guestService.saveGuest(guest);
        return "redirect:/";
        }


/*
    @PostMapping("/guests")
    public String saveGuest(@ModelAttribute("guest") Guest guest){
        guestService.save(guest);
    }

   */







    // Beidzās sadaļa, kas atbild par viesu reģistru
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
/*
    @PostMapping("/WelcomeToSeeInvitation")
    public String letGuestSeeInvitation(InvitationOpeningRequest invitationOpeningRequest, HttpServletResponse response) {
        System.out.println(invitationOpeningRequest);
        try {
            Guest loggedInGuest = this.guestService.verifyGuest(invitationOpeningRequest.guestEmail, invitationOpeningRequest.guestProjectCode);
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
*/
  /*  @GetMapping("/guestRegister")
    public String showRegisterPage() {
        return "guestRegister";*/
/*


    */


    }

