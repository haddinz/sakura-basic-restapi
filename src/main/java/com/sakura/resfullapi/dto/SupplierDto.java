package com.sakura.resfullapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class SupplierDto {
    @NotEmpty(message = "name is required")
    private String name;
    @NotEmpty(message = "name is required")
    private String address;
    @NotEmpty(message = "name is required")
    @Email(message = "email format wrong")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
