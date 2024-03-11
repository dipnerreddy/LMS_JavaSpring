package com.dipner.software.beans;

import jakarta.persistence.*;

@Entity
@Table(name = "login")
public class LoginUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_sequence")
    @SequenceGenerator(name = "login_sequence", sequenceName = "login_seq", allocationSize = 1)
    @Column(name = "login_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public LoginUsers(String email, String password) {
        this.email = email;
        this.password = password;
    }


    // getter and setter
    public LoginUsers() {

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
}
