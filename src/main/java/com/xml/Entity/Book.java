package com.xml.Entity;

public class Book {

    private String id;
    private String name;
    private String author;
    private String year;
    private String price;
    private String language;

    public Book(){}

    public Book(String id, String name, String author, String year, String price, String language) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.price = price;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public String getPrice() {
        return price;
    }

    public String getLanguage() {
        return language;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        return !(author != null ? !author.equals(book.author) : book.author != null)
                && !(id != null ? !id.equals(book.id) : book.id != null)
                && !(language != null ? !language.equals(book.language) : book.language != null)
                && !(name != null ? !name.equals(book.name) : book.name != null)
                && !(price != null ? !price.equals(book.price) : book.price != null)
                && !(year != null ? !year.equals(book.year) : book.year != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}
