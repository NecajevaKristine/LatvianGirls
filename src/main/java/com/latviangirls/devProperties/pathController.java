package com.latviangirls.devProperties;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("spring")
public class pathController {

    private final ConfigProperties pathConfig;

    public pathController(ConfigProperties pathConfig){
        this.pathConfig=pathConfig;
    }

    @GetMapping
    public Map<String, String> getAllProps(){
        return Map.of("mailPassword", pathConfig.mailPassword(),
        "mailUsername", pathConfig.mailUsername(),
        "datasourcePassword", pathConfig.datasourcePassword());
    }

}
