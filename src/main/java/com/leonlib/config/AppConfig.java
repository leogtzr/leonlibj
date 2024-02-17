package com.leonlib.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import jakarta.validation.constraints.AssertTrue;
import org.springframework.validation.annotation.Validated;
import static org.springframework.util.StringUtils.hasText;

@Component
@Validated
@ConfigurationProperties("app.leonlib")
public class AppConfig {
    
    private String mainAppUser;
    private String captchaSiteKey;

    private boolean isValid(final String property) {
        return hasText(property) && 
                !(property.startsWith("${") && property.endsWith("}"));
    }

    @AssertTrue
    public boolean isMainAppUserValid() {
        return isValid(this.mainAppUser);
    }

    @AssertTrue
    public boolean isCaptchaSiteKeyValid() {
        return isValid(this.captchaSiteKey);
    }

    @Bean
    public String getMainAppUser() {
        return mainAppUser;
    }

    public void setMainAppUser(final String mainAppUser) {
        this.mainAppUser = mainAppUser;
    }

    @Bean
    public String getCaptchaSiteKey() {
        return this.captchaSiteKey;
    }

    public void setCaptchaSiteKey(final String captchaSiteKey) {
        this.captchaSiteKey = captchaSiteKey;
    }
    
}
