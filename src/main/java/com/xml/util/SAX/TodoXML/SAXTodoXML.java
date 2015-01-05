package com.xml.util.SAX.TodoXML;

import com.xml.util.SAX.Entity.Book;
import com.xml.util.SAX.Handler.SAXParserHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public final class SAXTodoXML {

    private SAXTodoXML(){

    }

    public static void ReadXml(String xmlUrl){
        try {
            //获取一个SAXParserFactory的实例
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //通过factory获取SAXParser的实例
            SAXParser saxParser = factory.newSAXParser();
            //创建SAXParserHandler对象
            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(xmlUrl, handler);

            System.out.println("------------分析bookList----------------");
            for (Book book : handler.getBookList()){
                System.out.println(book.getId());
                System.out.println(book.getName());
                System.out.println(book.getAuthor());
                System.out.println(book.getYear());
                System.out.println(book.getPrice());
                System.out.println(book.getLanguage());
                System.out.println("----------------------------");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReadXml("src/main/resources/books.xml");
    }

}
