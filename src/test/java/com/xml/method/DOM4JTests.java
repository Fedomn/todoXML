package com.xml.method;

import com.xml.Controllers.DOM4J.DOM4JTodoXML;
import com.xml.Entity.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DOM4JTests {

    private ArrayList<Book> bookList1 = new ArrayList<Book>();
    private ArrayList<Book> bookList2 = new ArrayList<Book>();
    private Book book1;
    private Book book2;
    private Book book3;


    @Before
    public void setUp(){
        book1 = new Book("001", "分析模式", "Martin Flower",
                "2000", "58", null);
        book2 = new Book("002", "重构", "Martin Flower",
                "2000", "50", "English");
        book3 = new Book("003", "UML", "Martin Flower",
                null, "30", "Chinese");

        bookList1.addAll(Arrays.asList(book1, book2, book3));
    }

    @Test
    public void DOM4JReadTest(){
        List<Book> resultBookStore = new ArrayList<Book>();
        DOM4JTodoXML.ReadXml("src/main/resources/books.xml", resultBookStore);

        assertEquals(bookList1, bookList2);
    }

    @Test
    public void DOM4JWriteTest(){
        DOM4JTodoXML.WriteXml("src/main/resources/DOM4J.xml");
    }



}
