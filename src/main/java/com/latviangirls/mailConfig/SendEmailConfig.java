package com.latviangirls.mailConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;
@Configuration
public class SendEmailConfig {

    @Value("${app.sendgrid.key}")
    private String appKey;

    @Bean
    public SendGrid getSendGrid() {
      return new SendGrid(appKey);
    }
}











