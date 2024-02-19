package com.leonlib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "oauth_identifier", nullable = false)
    private String oauthIdentifier;

    public User() {}

    public User(final String userId, final String email, final String name, final String oauthIdentifier) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.oauthIdentifier = oauthIdentifier;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getOauthIdentifier() {
        return oauthIdentifier;
    }

    public void setOauthIdentifier(final String oauthIdentifier) {
        this.oauthIdentifier = oauthIdentifier;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", email=" + email + ", name=" + name + ", oauthIdentifier=" + oauthIdentifier
                + "]";
    }
}
