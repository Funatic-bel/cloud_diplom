package com.example.cloud.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    private String token;

    private String login;

    public Token() {}

    public Token(String token, String login) {
        this.token = token;
        this.login = login;
    }

    public String getToken() { return token; }

    public String getLogin() { return login; }

    public void setToken(String token) { this.token = token; }

    public void setLogin(String login) { this.login = login; }
}