package com.xml.util.DOM;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public final class DOMTodoXML {

    private DOMTodoXML(){
    }

    public static void ReadXml(String xmlUrl){
        try {
            //创建DOM解析器的工厂实例
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //从DOM工厂中获得DOM解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //把要解析的xml文档读入DOM解析器
            Document doc = dbBuilder.parse(xmlUrl);
            //得到文档名称为book的元素的节点列表
            NodeList nodeList = doc.getElementsByTagName("book");

            System.out.println("------------------------------------------------------");
            System.out.println("遍历集合 获取每个book节点属性");
            System.out.println("------------------------------------------------------");
            //遍历集合 获取每个book节点属性
            for (int i=0; i<nodeList.getLength(); i++){
                //得到一个结点
                Node node = nodeList.item(i);
                //得到该结点的所有属性值
                NamedNodeMap attrs = node.getAttributes();

                System.out.print("第" + (i + 1) + "本书：");

                //遍历所有属性
                for (int j=0; j<attrs.getLength(); j++){
                    //得到一个属性
                    Node attr = attrs.item(j);
                    //插入属性名->属性值
                    System.out.println("属性名："+attr.getNodeName()+" 属性值："+attr.getNodeValue());
                }
            }

            System.out.println("------------------------------------------------------");
            System.out.println("遍历集合 获取每个book节点子节点（已经知道子结点名称）通过Element");
            System.out.println("------------------------------------------------------");
            //遍历集合 获取每个book节点子节点（已经知道子结点名称）通过Element
            for (int i=0; i<nodeList.getLength(); i++){
                //得到一个结点
                Element node = (Element)nodeList.item(i);

                System.out.println("第" + (i + 1) + "本书：");

                //获取指定节点值
                System.out.print("子节点：");
                System.out.println(
                        "name: "+node.getElementsByTagName("name").item(0).getFirstChild().getNodeValue()+
                        " author: "+node.getElementsByTagName("author").item(0).getFirstChild().getNodeValue()+
                        " year: "  +node.getElementsByTagName("year").item(0).getFirstChild().getNodeValue()+
                        " price: " +node.getElementsByTagName("price").item(0).getFirstChild().getNodeValue()
                );
            }

            System.out.println("------------------------------------------------------");
            System.out.println("遍历集合 获取每个book节点子节点（不知道子结点名称）");
            System.out.println("------------------------------------------------------");
            //遍历集合 获取每个book节点子节点（不知道子结点名称）
            for (int i=0; i<nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                NodeList childNodes = node.getChildNodes();
                System.out.println("第"+(i+1)+"本书：");
                for (int j=0; j<childNodes.getLength(); j++){
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Node child = childNodes.item(j);
                        System.out.print("节点名：" + child.getNodeName());
                        System.out.println(" 节点值：" + child.getFirstChild().getNodeValue());
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void WriteXml(String xmlUrl){
        try {
            //创建DOM解析器的工厂实例
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //从DOM工厂中获得DOM解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //创建文档树模型对象
            Document doc = dbBuilder.newDocument();

            //创建bookstore元素
            Element bookstore = doc.createElement("bookstore");
            //创建book元素
            Element book = doc.createElement("book");
            //设置元素book的属性id为001
            book.setAttribute("id","001");
            //创建名称为Name的元素
            Element name = doc.createElement("name");
            //创建名称为 设计模式 的文本节点并作为子节点添加到name元素中
            name.appendChild(doc.createTextNode("设计模式"));
            //将name子元素添加到book中
            book.appendChild(name);

            //添加author到book中
            Element author = doc.createElement("author");
            author.appendChild(doc.createTextNode("Martin Flower"));
            book.appendChild(author);

            //添加year到book中
            Element year = doc.createElement("year");
            year.appendChild(doc.createTextNode("2000"));
            book.appendChild(year);

            //添加price到book中
            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode("58"));
            book.appendChild(price);

            //bookstore添加book结点
            bookstore.appendChild(book);
            //添加到文档树中
            doc.appendChild(bookstore);

            //写入部分
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //设置换行
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File(xmlUrl)));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        ReadXml("src/main/resources/books.xml");
        WriteXml("src/main/resources/DOMCreate_books.xml");
    }
}
