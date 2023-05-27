package com.latviangirls.mainPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/entry")
    public String displayMainPage(){
        return "entry";
    }
}