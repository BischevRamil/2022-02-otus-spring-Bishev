package ru.otus.hw_03.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Data
@Configuration
@ConfigurationProperties(prefix = "application")
public class YamlProps {
    private Locale locale;
    private String fileName;
    private Integer passBorder;
}
