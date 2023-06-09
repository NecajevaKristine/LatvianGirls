package com.latviangirls.users;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController (UserService userService){
        this.userService = userService;
    }


    @PostMapping("/register")
    public String handleUserRegistration (User user){
        try{
            this.userService.createUser(user);
            return "redirect:entry?status=REGISTER_SUCCESS&message=Registration successful! Please login to continue!";
        }catch(Exception exception){
            exception.printStackTrace();
            return "redirect:entry?status=REGISTER_FAILED&message=Registration failed! Try again!";
        }
    }
    @PostMapping("/login")
    public String handleLogin(LoginRequest loginRequest, HttpServletResponse response){
        try {
            User loggedInUser = this.userService.verifyUser(loginRequest.nickName, loginRequest.password);
            if (loggedInUser == null) throw new RuntimeException("User not found");

            Cookie cookie = new Cookie("loggedInUserId", loggedInUser.getId().toString());
            cookie.setMaxAge(60*60*60*24);
            response.addCookie(cookie);

            return "redirect:profile";
        }catch (Exception exception){
            return "redirect:entry?status=LOGIN_FAILED&message=Login failed! "+ exception.getMessage();
        }
    }

    @GetMapping("/logout")
    public String logout(
            @CookieValue(value = "loggedInUserId", defaultValue = "") String userId,
            HttpServletResponse response
    ){
        try {
            Cookie cookie = new Cookie("loggedInUserId", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);

            return "redirect:profile?status=LOGOUT_SUCCESS&message=You logged out successfully";
        } catch (Exception exception){
            return "redirect:login?status=LOGOUT_FAILED&message=" + exception.getMessage();
        }
    }

}
