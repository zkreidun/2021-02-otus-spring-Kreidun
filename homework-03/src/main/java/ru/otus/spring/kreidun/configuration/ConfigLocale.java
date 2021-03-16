package ru.otus.spring.kreidun.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@ConfigurationProperties("config")
public class ConfigLocale {

    private Locale locale;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {

        return  (this.locale.toString()=="")? Locale.getDefault(): this.locale;
    }
}
