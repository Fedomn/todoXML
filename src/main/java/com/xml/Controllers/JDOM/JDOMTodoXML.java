package com.xml.Controllers.JDOM;

import com.xml.Entity.Book;
import com.xml.Entity.BookStore;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.*;
import java.util.List;

public final class JDOMTodoXML {

    public static void ReadXml(String xmlUrl) {
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            //创建一个输入流加载XML文件
            InputStream in = new FileInputStream(xmlUrl);
            //将输入流转换为UTF-8格式
            InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
            Document document = saxBuilder.build(inputStreamReader);
            //获取根节点bookstore
            Element rootElement = document.getRootElement();
            //获取根节点下的子节点的list集合
            List<Element> bookList = rootElement.getChildren();
            BookStore bookStore = new BookStore();
            for (Element book : bookList) {
                Book bookEntity = new Book();

                System.out.println("-----开始解析第" + bookList.indexOf(book) + "本书-----");
                //解析book属性(不知道属性名)
                List<Attribute> attributes = book.getAttributes();
                for (Attribute attr : attributes) {
                    String attrname = attr.getName();
                    String attrvalue = attr.getValue();
                    System.out.println("属性名：" + attrname +
                            " 属性值：" + attrvalue);
                    if (attrname.equals("id")){
                        bookEntity.setId(attrvalue);
                    }

                }
                //解析book节点子节点
                List<Element> bookChilds = book.getChildren();
                for (Element child : bookChilds) {
                    String childName = child.getName();
                    String childValue = child.getValue();
                    System.out.println("节点名：" + childName +
                            " 节点值：" + childValue);
                    if (childName.equals("name")){
                        bookEntity.setName(childValue);
                    }else if (childName.equals("author")){
                        bookEntity.setAuthor(childValue);
                    }else if (childName.equals("year")){
                        bookEntity.setYear(childValue);
                    }else if (childName.equals("price")){
                        bookEntity.setPrice(childValue);
                    }else if (childName.equals("language")){
                        bookEntity.setLanguage(childValue);
                    }
                }
                bookStore.setBook(bookEntity);
                System.out.println("-----结束解析第" + bookList.indexOf(book) + "本书-----");
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReadXml("src/main/resources/books.xml");
    }

}
