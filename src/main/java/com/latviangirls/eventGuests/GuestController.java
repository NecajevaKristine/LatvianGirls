package com.latviangirls.eventGuests;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuestController {

    private GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService){
        this.guestService = guestService;
    }

    @GetMapping("/zzzzzzzzzzzz")
    public String showGuestRegisterPage(){
        return "invitation";
    }

    @PostMapping("/invitation")
    public String handleGuestRegistration(Guest guest){
        try {
            this.guestService.createGuest(guest);
            return "redirect:zzzzzzzzzzzzzz?status=NewGuestRegistered";
        } catch (Exception exception){
            exception.printStackTrace();
            return "redirect:guestRegister?status=ERROR_IN_NEW_GUEST_REGISTRATION&message="+exception.getMessage();
        }
    }
/*
    @GetMapping("login")
    public String displayLoginPage(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "message", required = false) String message,
            Model model
    ){
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(LoginRequest loginRequest, HttpServletResponse response){
        try {
            User loggedInUser = this.userService.verifyUser(loginRequest.username, loginRequest.password);
            if (loggedInUser == null) throw new RuntimeException("User not found");

            // save user id to cookie / session
            Cookie cookie = new Cookie("loggedInUserId", loggedInUser.getId().toString());
            // cookie expiry is in seconds.
            // if you want it to last longer, should calculate how many seconds for the days you want
            cookie.setMaxAge(100000);
            // saving the cookie to the user browser
            response.addCookie(cookie);

            return "redirect:chat-room";
        } catch (Exception exception){
            return "redirect:login?status=LOGIN_FAILED&message=" + exception.getMessage();
        }
    }
*/
}
