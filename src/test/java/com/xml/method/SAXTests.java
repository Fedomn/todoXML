package com.xml.method;

import com.xml.Controllers.SAX.TodoXML.SAXTodoXML;
import com.xml.Entity.Book;
import org.junit.Test;

import java.util.List;

import static com.xml.entity.BookStoreTests.localXmlBookStore;
import static com.xml.entity.BookStoreTests.localXmlFileUrl;
import static org.junit.Assert.assertEquals;

public class SAXTests {


    @Test
    public void SAXReadTest(){
        List<Book> readXmlBookStore = SAXTodoXML.ReadXml(localXmlFileUrl);
        assertEquals(readXmlBookStore, localXmlBookStore);
    }

    @Test
    public void SAXWriteTest(){
        String SAXWriteUrl = "src/main/resources/SAX.xml";
        SAXTodoXML.WriteXml(SAXWriteUrl, SAXTodoXML.ReadXml(localXmlFileUrl));

        List<Book> readXmlBookStore =  SAXTodoXML.ReadXml(SAXWriteUrl);
        assertEquals(readXmlBookStore, localXmlBookStore);
    }

}
