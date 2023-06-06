package com.latviangirls.users;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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


    @GetMapping("/profile")
    public String displayUserPage(){
        return "userPage";
    }

    @PostMapping("/register")
    public String handleUserRegistration (User user){
        try{
            this.userService.createUser(user);
            return "redirect:entry?status=REGISTER_SUCCESS";
        }catch(Exception exception){
            exception.printStackTrace();
            return "redirect:entry?status=REGISTER_FAILED&message=Registration failed";
        }
    }
    @PostMapping("/login")
    public String handleLogin(LoginRequest loginRequest){
        try {
            User loggedInUser = this.userService.verifyUser(loginRequest.nickName, loginRequest.password);
            return "redirect:profile";
        }catch (Exception exception){
            return "redirect:entry?status=LOGIN_FAILED&message=Login failed"+ exception.getMessage();
        }
    }

    @GetMapping("/userPage")
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

