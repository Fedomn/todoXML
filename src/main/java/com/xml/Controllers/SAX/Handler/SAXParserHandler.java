package com.xml.Controllers.SAX.Handler;

import com.xml.Entity.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SAXParserHandler extends DefaultHandler{

    private int bookIndex = 0;

    private String value = null;

    private Book book = null;

    private ArrayList<Book> bookList = new ArrayList<Book>();

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    /**
     * 用来标志解析开始
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("-----SAX解析开始-----");
    }

    /**
     * 用来标志解析结束
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("-----SAX解析结束-----");
    }

    /**
     * 用来遍历XML文件的开始标签
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        //开始解析book元素属性
        if (qName.equals("book")){
            bookIndex++;
            book = new Book();
            System.out.println("开始遍历第" + bookIndex +"本书");
//            //已知book元素属性名称，根据属性名称获取属性值
//            String val = attributes.getValue("id");
//            System.out.println("book的属性值："+val);


            //不知道book元素下属性名称和个数
            int valNum = attributes.getLength();
            for (int i=0; i<valNum; i++){
                System.out.print("book元素的第" + (i + 1) + "个属性名：" + attributes.getQName(i));
                System.out.println(" 属性值："+attributes.getValue(i));
                if (attributes.getQName(i).equals("id")){
                    book.setId(attributes.getValue(i));
                }
            }
        }else if (!qName.equals("book") && !qName.equals("bookstore")){
            System.out.print("结点名是：" + qName + " ");
        }
    }

    /**
     * 用来遍历XML文件的结束标签
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        //判断book是否结束
        if (qName.equals("book")){
            bookList.add(book);
            book = null;
            System.out.println("结束遍历第" + bookIndex +"本书");
        }else if (qName.equals("name")){
            book.setName(value);
        }else if (qName.equals("author")){
            book.setAuthor(value);
        }else if (qName.equals("year")){
            book.setYear(value);
        }else if (qName.equals("price")){
            book.setPrice(value);
        }else if (qName.equals("language")){
            book.setLanguage(value);
        }
    }

    /**
     * 遇到结点时自动调用获取结点值
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        value = new String(ch, start, length);
        if (!value.trim().equals(""))
            System.out.println("结点值是：" + value);
    }
}
