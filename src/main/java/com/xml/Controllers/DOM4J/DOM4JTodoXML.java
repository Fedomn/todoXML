package com.xml.Controllers.DOM4J;

import com.xml.Entity.Book;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class DOM4JTodoXML {

    public static List<Book> ReadXml(String readXmlUrl) {
        List<Book> bookStore = new ArrayList<Book>();
        try {
            SAXReader saxReader = new SAXReader();
            //通过saxReader的read方法加载XML
            Document document = saxReader.read(new File(readXmlUrl));
            //获取根节点
            Element bookStoreNode = document.getRootElement();
            //获取迭代器
            Iterator it = bookStoreNode.elementIterator();
            //遍历迭代器
            while (it.hasNext()) {
                Element bookNode = (Element) it.next();
                Book book = new Book();
                //获取book属性
                List<Attribute> bookAttrs = bookNode.attributes();
                for (Attribute attr : bookAttrs) {
                    if (attr.getName() == "id") {
                        book.setId(attr.getValue());
                    }
                }
                Iterator itChild = bookNode.elementIterator();
                while (itChild.hasNext()){
                    Element bookNodeChild = (Element)itChild.next();
                    String childName = bookNodeChild.getName();
                    String childValue = bookNodeChild.getStringValue();
                    if (childName == "name") {
                        book.setName(childValue);
                    }else if (childName == "author"){
                        book.setAuthor(childValue);
                    }else if (childName == "year"){
                        book.setYear(childValue);
                    }else if (childName == "price"){
                        book.setPrice(childValue);
                    }else if (childName == "language"){
                        book.setLanguage(childValue);
                    }
                }
                bookStore.add(book);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return bookStore;
    }

    public static void WriteXml(String xmlUrl, List<Book> bookStore){
        //创建document代表整个XML文档
        Document document = DocumentHelper.createDocument();
        Element bookStoreNode = document.addElement("bookstore");

        for (Book book : bookStore){
            Element bookNode = bookStoreNode.addElement("book");
            bookNode.addAttribute("id", book.getId());
            if (book.getName() != ""){
                Element name = bookNode.addElement("name");
                name.setText(book.getName());
            }
            if (book.getAuthor() != ""){
                Element author = bookNode.addElement("author");
                author.setText(book.getAuthor());
            }
            if (book.getYear() != ""){
                Element year = bookNode.addElement("year");
                year.setText(book.getYear());
            }
            if (book.getPrice() != ""){
                Element price = bookNode.addElement("price");
                price.setText(book.getPrice());
            }
            if (book.getLanguage() != ""){
                Element language = bookNode.addElement("language");
                language.setText(book.getLanguage());
            }
        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        File file = new File(xmlUrl);

        try {
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            //设置不转义
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

