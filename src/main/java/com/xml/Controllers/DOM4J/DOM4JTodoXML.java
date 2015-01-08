package com.xml.Controllers.DOM4J;

import com.xml.Entity.Book;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public final class DOM4JTodoXML {

    public static void ReadXml(String xmlUrl, List<Book> bookList) {
        try {
            SAXReader saxReader = new SAXReader();
            //通过saxReader的read方法加载XML
            Document document = saxReader.read(new File(xmlUrl));
            //获取根节点
            Element bookStore = document.getRootElement();
            //获取迭代器
            Iterator it = bookStore.elementIterator();
            //遍历迭代器
            while (it.hasNext()) {
                Element book = (Element) it.next();
                Book bookTemp = new Book();
                //获取book属性
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs) {
                    if (attr.getName() == "id") {
                        bookTemp.setId(attr.getValue());
                    }
                }
                Iterator itChild = book.elementIterator();
                while (itChild.hasNext()){
                    Element bookChild = (Element)itChild.next();
                    String childName = bookChild.getName();
                    String childValue = bookChild.getStringValue();
                    if (childName == "name") {
                        bookTemp.setName(childValue);
                    }else if (childName == "author"){
                        bookTemp.setAuthor(childValue);
                    }else if (childName == "year"){
                        bookTemp.setYear(childValue);
                    }else if (childName == "price"){
                        bookTemp.setPrice(childValue);
                    }else if (childName == "language"){
                        bookTemp.setLanguage(childValue);
                    }
                }
                bookList.add(bookTemp);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void WriteXml(String xmlUrl){
        //创建document代表整个XML文档
        Document document = DocumentHelper.createDocument();
        Element bookstore = document.addElement("bookstore");

        Element book = bookstore.addElement("book");
        book.addAttribute("ID", "001");

        Element name = book.addElement("name");
        name.setText("分析模式");
        Element author = book.addElement("author");
        author.setText("Martin Flower");
        Element year = book.addElement("year");
        year.setText("2000");
        Element price = book.addElement("price");
        price.setText("100");

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

