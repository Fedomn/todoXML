package com.xml.Controllers.SAX.TodoXML;

import com.xml.Controllers.SAX.Handler.SAXParserHandler;
import com.xml.Entity.Book;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public final class SAXTodoXML {

    private SAXTodoXML(){

    }

    public static List<Book> ReadXml(String xmlUrl){
        try {
            //1、获取一个SAXParserFactory的实例
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //2、通过factory获取SAXParser的实例
            SAXParser saxParser = factory.newSAXParser();
            //3、创建SAXParserHandler对象
            SAXParserHandler handler = new SAXParserHandler();
            saxParser.parse(xmlUrl, handler);
            return handler.getBookStore();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    public static void WriteXml(String url, List<Book> bookStore){
        //1、创建一个TransformerFactory类的对象
        SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
        try {
            //2、通过saxTransformerFactory创建TransformerHandler对象
            TransformerHandler handler = saxTransformerFactory.newTransformerHandler();
            //3、通过transformerHandler创建一个Transformer对象
            Transformer transformer = handler.getTransformer();
            //4、对transformer生成的XML设置属性
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //5、创建一个Result对象处理文件
            Result result = new StreamResult(new FileOutputStream(new File(url)));
            //6、是result与transformerHandler关联
            handler.setResult(result);
            //7、利用transformerHandler编写XML文件内容
            handler.startDocument();

            AttributesImpl attrs = new AttributesImpl();
            handler.startElement("", "", "bookstore", attrs);

            for (Book book : bookStore){
                attrs.clear();
                attrs.addAttribute("", "", "id", "", book.getId());
                handler.startElement("", "", "book", attrs);

                if (!book.getName().trim().equals("")){
                    attrs.clear();
                    handler.startElement("", "", "name", attrs);
                    handler.characters(book.getName().toCharArray(), 0, book.getName().length());
                    handler.endElement("", "", "name");
                }

                if (!book.getAuthor().trim().equals("")){
                    attrs.clear();
                    handler.startElement("", "", "author", attrs);
                    handler.characters(book.getAuthor().toCharArray(), 0, book.getAuthor().length());
                    handler.endElement("", "", "author");
                }

                if (!book.getYear().trim().equals("")){
                    attrs.clear();
                    handler.startElement("", "", "year", attrs);
                    handler.characters(book.getYear().toCharArray(),0, book.getYear().length());
                    handler.endElement("", "", "year");
                }

                if (!book.getPrice().trim().equals("")){
                    attrs.clear();
                    handler.startElement("", "", "price", attrs);
                    handler.characters(book.getPrice().toCharArray(), 0, book.getPrice().length());
                    handler.endElement("", "", "price");
                }

                if (!book.getLanguage().trim().equals("")){
                    attrs.clear();
                    handler.startElement("", "", "language", attrs);
                    handler.characters(book.getLanguage().toCharArray(), 0, book.getLanguage().length());
                    handler.endElement("", "", "language");
                }
                handler.endElement("", "", "book");
            }
            handler.endElement("", "", "bookstore");
            handler.endDocument();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }

}
