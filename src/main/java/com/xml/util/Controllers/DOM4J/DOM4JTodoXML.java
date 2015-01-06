package com.xml.util.Controllers.DOM4J;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class DOM4JTodoXML {

    public static void ReadXml(String xmlUrl) {
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
                //获取book属性
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs) {
                    attr.getName();
                    attr.getValue();
                }
                Iterator itChild = book.elementIterator();
                while (itChild.hasNext()){
                    Element bookChild = (Element)itChild.next();
                    bookChild.getName();
                    bookChild.getStringValue();
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}

