package edu.utep.cs.cs4330.booky;

public class Book {
    String title;
    String author;
    String genre;
    String isbn;

    public Book(String title, String author, String genre, String isbn){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { title = title; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { author = author; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { genre = genre; }

    public String getISBN() { return isbn; }

    public void setISBN(String ISBN) { this.isbn = isbn; }
}
