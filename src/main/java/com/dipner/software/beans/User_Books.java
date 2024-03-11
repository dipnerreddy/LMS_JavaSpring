package com.dipner.software.beans;


import jakarta.persistence.*;

@Entity
@Table(name = "user_books")
public class User_Books {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_books_sequence")
    @SequenceGenerator(name = "user_books_sequence", sequenceName = "user_books_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "bookSpecialID")
    private String bookSpecialID;

    @Column(name = "user_details")
    private String userMailId;


    public User_Books(String bookSpecialID, String userMailId) {
        this.bookSpecialID = bookSpecialID;
        this.userMailId = userMailId;
    }

    public User_Books() {

    }

    public String getBookSpecialID(String bookSpecialID) {
        return this.bookSpecialID;
    }

    public void setBookSpecialID(String bookSpecialID) {
        this.bookSpecialID = bookSpecialID;
    }

    public String getUserMailId() {
        return userMailId;
    }

    public String setUserMailId(String userMailId) {
        this.userMailId = userMailId;
        return userMailId;
    }
}
