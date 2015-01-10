package com.xml.method;

import com.xml.Controllers.DOM4J.DOM4JTodoXML;
import com.xml.Entity.Book;
import org.junit.Test;

import java.util.List;

import static com.xml.entity.BookStoreTests.localXmlBookStore;
import static com.xml.entity.BookStoreTests.localXmlFileUrl;
import static org.junit.Assert.assertEquals;

public class DOM4JTests {

    @Test
    public void DOM4JReadTest(){
        List<Book> readXmlBookStore = DOM4JTodoXML.ReadXml(localXmlFileUrl);
        assertEquals(readXmlBookStore, localXmlBookStore);
    }

    @Test
    public void DOM4JWriteTest(){
        String DOM4JWriteUrl = "src/main/resources/DOM4J.xml";
        DOM4JTodoXML.WriteXml(DOM4JWriteUrl, DOM4JTodoXML.ReadXml(localXmlFileUrl));

        List<Book> readXmlBookStore =  DOM4JTodoXML.ReadXml(DOM4JWriteUrl);
        assertEquals(readXmlBookStore, localXmlBookStore);
    }
}
