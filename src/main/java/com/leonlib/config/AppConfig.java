package com.leonlib.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import javax.validation.constraints.AssertTrue;
import org.springframework.validation.annotation.Validated;
import static org.springframework.util.StringUtils.hasText;

@Component
@Validated
@ConfigurationProperties("app.leonlib")
public class AppConfig {
    
    private String mainAppUser;
    private String captchaSiteKey;
    private String authDomain;
    private String authClientId;
    private String authClientSecret;
    private String authCallbackURL;
    
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

    @AssertTrue
    public boolean isAuthDomainValid() {
        return isValid(this.authDomain);
    }

    @AssertTrue
    public boolean isAuthClientIdValid() {
        return isValid(this.authClientId);
    }

    @AssertTrue
    public boolean isAuthClientSecretValid() {
        return isValid(this.authClientSecret);
    }

    @AssertTrue
    public boolean isAuthCallbackURLValid() {
        return isValid(this.authCallbackURL);
    }

    @Bean
    public String getMainAppUser() {
        return mainAppUser;
    }

    @Bean
    public String getCaptchaSiteKey() {
        return this.captchaSiteKey;
    }

    @Bean
    public String getAuthDomain() {
        return this.authDomain;
    }

    @Bean
    public String getAuthClientId() {
        return this.authClientId;
    }

    @Bean
    public String getAuthClientSecret() {
        return this.authClientSecret;
    }

    @Bean
    public String getAuthCallbackURL() {
        return this.authCallbackURL;
    }

    public void setMainAppUser(final String mainAppUser) {
        this.mainAppUser = mainAppUser;
    }

    public void setCaptchaSiteKey(final String captchaSiteKey) {
        this.captchaSiteKey = captchaSiteKey;
    }

    public void setAuthDomain(final String authDomain) {
        this.authDomain = authDomain;
    }

    public void setAuthClientId(final String authClientId) {
        this.authClientId = authClientId;
    }

    public void setAuthClientSecret(final String authClientSecret) {
        this.authClientSecret = authClientSecret;
    }

    public void setAuthCallbackURL(final String authCallbackURL) {
        this.authCallbackURL = authCallbackURL;
    }
}
