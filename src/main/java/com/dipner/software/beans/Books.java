package com.dipner.software.beans;


import jakarta.persistence.*;


@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_sequence")
    @SequenceGenerator(name = "books_sequence", sequenceName = "books_seq", allocationSize = 1)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "issued")
    private boolean issued;

    @Column(name = "bookSpecialID")
    private String bookSpecialID;

    // getters and setters

    public Books(String bookName, String author, String category, boolean issued, String bookSpecialID) {
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.issued = issued;
        this.bookSpecialID = bookSpecialID;
    }


    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public String getBookSpecialID() {
        return bookSpecialID;
    }

    public void setBookSpecialID(String bookSpecialID) {
        this.bookSpecialID = bookSpecialID;
    }
}
