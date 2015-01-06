package com.xml.util.Entity;

import java.util.ArrayList;

public class Bookstore {

    private ArrayList<Book> booksList = new ArrayList<Book>();

    public void setBook(Book book){
        booksList.add(book);
    }

    public ArrayList<Book> getBooksList(){
        return booksList;
    }
}
