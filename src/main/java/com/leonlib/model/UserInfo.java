package com.leonlib.model;

public class UserInfo {
    private String sub;
    private String name;
    private String nickname;
    private String picture;
    private String email;
    private boolean verified;
    
    public String getSub() {
        return sub;
    }

    public void setSub(final String sub) {
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public String getPicture() {
        return picture;
    }
    
    public void setPicture(final String picture) {
        this.picture = picture;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public boolean isVerified() {
        return verified;
    }
    
    public void setVerified(final boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "UserInfo [sub=" + sub + ", name=" + name + ", nickname=" + nickname + ", picture=" + picture
                + ", email=" + email + ", verified=" + verified + "]";
    }
}
