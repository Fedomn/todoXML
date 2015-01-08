package com.xml.entity;

import com.xml.Entity.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BookstoreTests {

    private ArrayList<Book> bookList1 = new ArrayList<Book>();
    private ArrayList<Book> bookList2 = new ArrayList<Book>();
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book1Back;


    @Before
    public void setUp(){
        book1 = new Book("001", "分析模式", "Martin Flower",
                "2000", "58", null);
        book2 = new Book("002", "重构", "Martin Flower",
                "2000", "50", "English");
        book3 = new Book("003", "UML", "Martin Flower",
                null, "30", "Chinese");
        book1Back = new Book("001", "分析模式", "Martin Flower",
                "2000", "58", null);

        bookList1.addAll(Arrays.asList(book1, book2, book3));
        bookList2.addAll(Arrays.asList(book1, book2, book3));
    }


    @Test
    public void bookTest(){
        assertEquals(book1, book1Back);

        assertEquals(book1, book2);

        assertThat(book1.getId(), is("001"));
    }



    @Test
    public void bookstoreTest(){
        assertEquals(bookList1, bookList2);
    }
}
