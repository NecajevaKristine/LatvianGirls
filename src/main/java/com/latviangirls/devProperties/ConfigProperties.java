package com.latviangirls.devProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("path")
public record ConfigProperties(String datasourcePassword, String mailUsername, String mailPassword) {


}
