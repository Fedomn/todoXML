package com.xml.Controllers.DOM4J;

import com.xml.Entity.Book;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class DOM4JTodoXML {

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

}

