package com.chattingo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String profile;

    // WRITE_ONLY: the password can still be read from an incoming signup/login
    // request body, but it will never be included when a User is serialized
    // back out in a response - it was previously leaking the bcrypt hash in
    // every chat/message API response and WebSocket broadcast that embedded a
    // User object.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(int id, String name, String email, String profile, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profile = profile;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", profile=" + profile + ", password="
                + password + "]";
    }

}
