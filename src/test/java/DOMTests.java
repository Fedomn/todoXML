import com.fedomn.parseXML.DOMTodoXML;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DOMTests {

    private static final String XmlUrl = "src/main/resources/books.xml";

    private static final String TagName = "id";

    private DOMTodoXML doMtodoXML;
    private Map<Integer, Map<String,String>> nodeRes = new HashMap<Integer, Map<String, String>>();

    @Before
    public void setUp() throws ParserConfigurationException, SAXException, IOException {
        doMtodoXML = new DOMTodoXML(XmlUrl);
    }

    /**
     * 测试1：找不到结点
     */
    @Test
    public void getNodePropertyTest1(){
        String NodeName = "NoNode";

        doMtodoXML.getNodeProperty(NodeName, nodeRes);
        assertEquals(nodeRes.isEmpty(), true);

        nodeRes.clear();

        doMtodoXML.getNodeProperty(NodeName, TagName, nodeRes);
        assertEquals(nodeRes.isEmpty(), true);
    }


    /**
     * 测试2：找到结点，找不到属性名
     */
    @Test
    public void getNodePropertyTest2(){
        String NodeName = "bookstore";
        String TagName = "id";
        doMtodoXML.getNodeProperty(NodeName, TagName, nodeRes);
        assertEquals(nodeRes.get(1).get(TagName), "");
    }


    /**
     * 测试3：找到结点，找到属性名
     */
    @Test
    public void getNodePropertyTest3(){
        String NodeName = "book";
        String TagName = "id";
        doMtodoXML.getNodeProperty(NodeName, TagName, nodeRes);

        assertEquals(nodeRes.get(1).get(TagName), "1");
        assertEquals(nodeRes.get(2).get(TagName), "2");
    }

    /**
     * 测试4：找到结点，打印所有属性名+属性值
     */
    @Test
    public void getNodePropertyTest4(){
        String NodeName = "book";
        doMtodoXML.getNodeProperty(NodeName, nodeRes);

        for (Integer t : nodeRes.keySet()){
            System.out.println("第 "+t+"个"+NodeName+"结点：");
            for (String s : nodeRes.get(t).keySet()){
                System.out.println("属性名："+s+" 属性值："+nodeRes.get(t).get(s));
            }
        }
    }
}
