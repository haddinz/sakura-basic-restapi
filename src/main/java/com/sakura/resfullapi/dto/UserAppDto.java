package com.sakura.resfullapi.dto;

public class UserAppDto {
    private String fullName;
    private String email;
    private String password;
    private String userAppRole;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserAppRole() {
        return userAppRole;
    }

    public void setUserAppRole(String userAppRole) {
        this.userAppRole = userAppRole;
    }
}
