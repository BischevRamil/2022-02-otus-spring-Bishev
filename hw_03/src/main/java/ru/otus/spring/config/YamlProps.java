package ru.otus.spring.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
public class YamlProps {

    private Locale locale;
    private String fileName;
    private Integer passBorder;
}
