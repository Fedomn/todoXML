package com.xml.Entity;

import java.util.ArrayList;

public class Bookstore {

    private ArrayList<Book> booksList = new ArrayList<Book>();

    public void setBook(Book book){
        booksList.add(book);
    }

    public ArrayList<Book> getBooksList(){
        return booksList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bookstore)) return false;

        Bookstore bookstore = (Bookstore) o;
        ArrayList<Book> bookList = bookstore.getBooksList();

        for(Book book : bookList){
            if (!book.equals(o)) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 31;
        for (Book book : booksList){
            result += book.hashCode();
        }
        return result;
    }
}
