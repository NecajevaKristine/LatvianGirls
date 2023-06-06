package com.latviangirls;
import com.latviangirls.devProperties.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class LatvianGirlsApplication{

    public static void main(String[] args) {
        SpringApplication.run(LatvianGirlsApplication.class, args);
    }

    @Autowired
    private ConfigProperties configProperties;
}
