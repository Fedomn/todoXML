package com.xml.util;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public final class DOMTodoXML {

    private DOMTodoXML(){
    }

    public static void ReadXml(String xmlUrl){
        try {
            //创建DOM解析器的工厂实例
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //从DOM工厂中获得DOM解析器
            DocumentBuilder dbBuilder = null;
            dbBuilder = dbFactory.newDocumentBuilder();
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

    public static void main(String[] args){
        String XmlUrl = "src/main/resources/books.xml";
        ReadXml(XmlUrl);
    }



}
