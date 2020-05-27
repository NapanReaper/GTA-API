/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group.travel.api.dtos;

/**
 *
 * @author hoang
 */
public class AuthToken {

    private String token;
    private Long id;
    private String name;
    private String email;
    private Roles roles;

    public AuthToken() {

    }

    public AuthToken(String token, String name, String email, Roles roles) {
        this.token = token;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public AuthToken(String token, Long id, String name, String email, Roles roles) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
