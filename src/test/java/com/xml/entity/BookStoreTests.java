package com.xml.entity;

import com.xml.Entity.Book;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BookStoreTests {

    public static final List<Book> localXmlBookStore;

    public static final List<Book> testXmlBookStore;

    public static final Book localXmlBookId001;
    public static final Book localXmlBookId002;
    public static final Book localXmlBookId003;

    public static final Book testXmlBookId001;

    static {
        localXmlBookId001 = new Book(
                "001", "分析模式", "Martin Flower", "2000", "58", null);
        localXmlBookId002 = new Book(
                "002", "重构", "Martin Flower", "2000", "50", "English");
        localXmlBookId003 = new Book(
                "003", "UML", "Martin Flower", null, "30", "Chinese");

        testXmlBookId001 = new Book(
                "001", "分析模式", "Martin Flower", "2000", "58", null);

        testXmlBookStore = Arrays.asList(
                localXmlBookId001, localXmlBookId002, localXmlBookId003);

        localXmlBookStore = Arrays.asList(
                localXmlBookId001, localXmlBookId002, localXmlBookId003);
    }

    @Test
    public void bookEqualsTest(){
        assertEquals(localXmlBookId001, localXmlBookId001);

        assertThat(testXmlBookId001.getId(), is("001"));
    }

    @Test
    public void bookStoreEqualsTest(){
        assertEquals(testXmlBookStore, localXmlBookStore);
    }
}
