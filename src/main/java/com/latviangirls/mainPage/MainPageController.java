package com.latviangirls.mainPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPageController {
    @GetMapping("/")
    public String displayMainPage(){
        return "redirect:entry";
    }
    @GetMapping("/entry")
    public String displayEntryPage(Model model, @RequestParam(required = false) String message){
        model.addAttribute("message", message);
        return "entry";
    }
}
