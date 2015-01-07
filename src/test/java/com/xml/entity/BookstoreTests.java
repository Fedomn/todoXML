package com.xml.entity;

import com.xml.util.Entity.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BookstoreTests {

    private ArrayList<Book> bookList1 = new ArrayList<Book>();
    private ArrayList<Book> bookList2 = new ArrayList<Book>();
    private Book book1;
    private Book book2;
    private Book book3;


    @Before
    public void setUp(){
        book1 = new Book("001", "Refactoring", "Martin Flower",
                "2010", "100", "English");
        book2 = new Book("001", "Refactoring", "Martin Flower",
                "2010", "100", "English");
        book3 = new Book("003", "Refactoring", "Martin Flower",
                "2010", "100", "English");

        bookList1.addAll(Arrays.asList(book1, book2, book3));
        bookList2.addAll(Arrays.asList(book1, book2, book3));
    }


    @Test
    public void bookTest(){
        assertEquals(book1, book2);
    }

    @Test
    public void bookstoreTest(){
        assertEquals(bookList1, bookList2);
    }



}
