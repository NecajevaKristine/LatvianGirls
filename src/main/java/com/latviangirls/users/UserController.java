package com.latviangirls.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController (UserService userService){
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(){
        return "guestRegister";
    }

    @PostMapping("/register")
    public String handleUserRegistration (User user){
        try{
            this.userService.createUser(user);
            return "redirect:register?status=REGISTER_SUCCESS";
        }catch(Exception exception){
            exception.printStackTrace();
            return "redirect:register?status=REGISTER_FAILED& message="+exception.getMessage();
        }
    }

    @GetMapping("/login")
    public String displayLoginPage(
            @RequestParam(name="status", required=false) String status,
            @RequestParam (name="message", required=false) String message,
            Model model
    ){
        model.addAttribute ("status", status);
        model.addAttribute ("message", message);
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(LoginRequest loginRequest){
        try {
            User loggedInUser = this.userService.verifyUser(loginRequest.nickName, loginRequest.password);
            return "redirect:userPage";
        }catch (Exception exception){
            return "redirect:login?status=LOGIN_FAILED&message=" + exception.getMessage();
        }

    }

}

