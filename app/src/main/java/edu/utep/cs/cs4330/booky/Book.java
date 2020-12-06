package edu.utep.cs.cs4330.booky;

public class Book {
    String title;
    String author;
    String genre;
    String isbn;

    public Book(){
    }

    public Book(String title, String author, String genre, String isbn){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
    }

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return this.author; }

    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return this.genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public String getISBN() { return this.isbn; }

    public void setISBN(String ISBN) { this.isbn = isbn; }
}
