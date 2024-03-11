package com.dipner.software.beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Long id;


    @Column(name = "user_name")
    private String username;


    @Column(name = "email")
    private String email;

    @Size(min = 6,max = 16, message = "Enter your Password with minimun length 6")
    @Column(name = "password")
    private String password;


    public Users() {

    }

    public String getUsername() {
        return username;
    }


    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
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
