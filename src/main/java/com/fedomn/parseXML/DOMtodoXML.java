package com.fedomn.parseXML;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DOMTodoXML {

    Document document = null;

    public DOMTodoXML(String xmlUrl) throws IOException, SAXException, ParserConfigurationException {
        document = getDocument(xmlUrl);
    }

    private Document getDocument(String xmlUrl) throws ParserConfigurationException, IOException, SAXException {
        //create DocumentBuilderFactory object
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //create newDocumentBuilder object
        DocumentBuilder db = dbf.newDocumentBuilder();
        //load xml by parse method(Document : org.w3c.dom)
        return db.parse(xmlUrl);
    }

    /**
     * @param nodeName 结点名称
     * @return 该名称的所以结点
     */
    private NodeList getNodeList(String nodeName){
        return document.getElementsByTagName(nodeName);
    }


    /**
     * 情况：不知道结点属性名，返回所有属性名+属性值
     * @param nodeName 结点的名称
     * @param nodeMap 解析结果存放的集合
     */
    public void getNodeProperty(String nodeName, Map<Integer, Map<String,String>> nodeMap){
        NodeList nodeList = getNodeList(nodeName);
        for (int i=0; i< nodeList.getLength(); i++){
            //得到一个结点
            Node node = nodeList.item(i);
            //得到该结点的所有属性值
            NamedNodeMap attrs = node.getAttributes();
            //生成一个属性map
            Map<String, String> propertyMap = new HashMap<String, String>();
            //遍历所有属性
            for (int j=0; j<attrs.getLength(); j++){
                //得到一个属性
                Node attr = attrs.item(j);
                //插入属性名->属性值
                propertyMap.put(attr.getNodeName(), attr.getNodeValue());
            }
            nodeMap.put(i+1, propertyMap);
        }
    }

    /**
     * 情况：知道结点属性名，返回所有属性名+属性值
     * @param nodeName 结点的名称
     * @param tagName 属性名
     * @param nodeMap 解析结果存放的集合
     */
    public void getNodeProperty(String nodeName, String tagName, Map<Integer, Map<String,String>> nodeMap){
        NodeList nodeList = getNodeList(nodeName);
        for (int i=0; i < nodeList.getLength(); i++){
            //生成一个属性map
            Map<String, String> propertyMap = new HashMap<String, String>();
            //将某一结点强制转换为Element类型
            Element book = (Element) nodeList.item(i);
            //得到tagName的属性值
            String attrValue = book.getAttribute(tagName);
            //保存属性名+属性值
            propertyMap.put(tagName, attrValue);
            nodeMap.put(i+1, propertyMap);
        }
    }

    //得到子节点
    public void getChildNode(String nodeName){
        NodeList nodeList = getNodeList(nodeName);
        for (int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            NodeList childNodes = node.getChildNodes();
            System.out.println("第"+(i+1)+"本书：");
            for (int j=0; j<childNodes.getLength(); j++){
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                    Node child = childNodes.item(j);
                    System.out.print("节点名：" + child.getNodeName());
                    System.out.println("节点值：" + child.getFirstChild().getNodeValue());
                }
            }
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DOMTodoXML domTodoXML = new DOMTodoXML("src/main/resources/books.xml");
        domTodoXML.getChildNode("book");
    }


}
