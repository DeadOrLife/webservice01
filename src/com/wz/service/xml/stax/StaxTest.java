package com.wz.service.xml.stax;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * 处理光标的形式来进行访问xml
 * @author jamesbean
 */
public class StaxTest {

    @Test
    public void test01(){
        InputStream is = null;
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            is = StaxTest.class.getResourceAsStream("books.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(is);

            while (reader.hasNext()){

                int type = reader.next();
                System.out.println();
                if (type == XMLStreamConstants.START_ELEMENT){
                    //标签起始节点
                    System.out.println(reader.getName());
                }else if(type == XMLStreamConstants.CHARACTERS){
                    //文本节点
                    System.out.println(reader.getText().trim());
                }else if(type == XMLStreamConstants.END_DOCUMENT) {
                    //标签结束节点
                    System.out.println("/" + reader.getName());
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    /**
     * 获取属性名与实现值
     */
    @Test
    public void test02(){
        InputStream is = null;
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            is = StaxTest.class.getResourceAsStream("books.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(is);
            while (reader.hasNext()){
                int type = reader.next();
                System.out.println();
                if (type == XMLStreamConstants.START_ELEMENT){
                    //标签起始节点
                    String name = reader.getName().toString();
                    if (name.equals("book")){
                        System.out.print(reader.getAttributeName(0 )+":"+reader.getAttributeValue(0));
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    /**
     * 根据标签名获取对应的标前中的值(文本节点值)
     */
    @Test
    public void test03(){
        InputStream is = null;
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            is = StaxTest.class.getResourceAsStream("books.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(is);
            while (reader.hasNext()){
                int type = reader.next();
                System.out.println();
                if (type == XMLStreamConstants.START_ELEMENT){
                    String name = reader.getName().toString();
                    //如果name等于title 或者 等于price 那么就输出标签中的值
                    //判断节点类型
                    if ("title".equals(name)){
                        System.out.println(reader.getElementText());
                    }
                    if ("price".equals(name)){
                        System.out.println(reader.getElementText());
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    /**
     *  基于迭代模型的方式
     */
    @Test
    public void test04(){
        InputStream is = null;
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            is = StaxTest.class.getResourceAsStream("books.xml");
            XMLEventReader  reader = factory.createXMLEventReader(is);
            while (reader.hasNext()){
                //通过XMLEvent来获取是否是某种节点类型
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()){
                    //通过event.asxxx转换节点
                    String name = event.asStartElement().getName().toString();
                    if ("title".equals(name)){
                        System.out.println(reader.getElementText());
                    }
                    if ("price".equals(name)){
                        System.out.println(reader.getElementText());
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     *  过滤器
     */
    @Test
    public void test05(){
        InputStream is = null;
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            is = StaxTest.class.getResourceAsStream("books.xml");
            //基于Filter的过滤方式 可以有效的过滤不用进操作的节点，效率会更高点
            XMLEventReader  reader = factory.createFilteredReader(factory.createXMLEventReader(is),
                    new EventFilter() {
                        @Override
                        public boolean accept(XMLEvent event) {
                            //返回true表示会显示，返回false表是不会显示
                            if (event.isStartElement()){
                                String name = event.asStartElement().getName().toString();
                                if ("title".equals(name)){
                                    return true;
                                }
                            }
                            return false;
                        }
                    });

            while (reader.hasNext()){
                //通过XMLEvent来获取是否是某种节点类型
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()){
                    //通过event.asxxx转换节点
                    String name = event.asStartElement().getName().toString();

                    if ("title".equals(name)){
                        System.out.println(reader.getElementText());
                    }
                    if ("price".equals(name)){
                        System.out.println(reader.getElementText());
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  XPath
     */
    @Test
    public void test06(){
        InputStream is = null;
        try {
            is = StaxTest.class.getResourceAsStream("books.xml");
            //1.创建文档处理对象
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //2.通过DocumentBuilder创建doc的文档对象
            Document doc = db.parse(is);
            //3.创建XPath对象
            XPath xPath = XPathFactory.newInstance().newXPath();
            //4.第一个参数是xpath，第二参数是Document对象
            NodeList list = (NodeList)xPath.evaluate("//book[@category='WEB']",doc, XPathConstants.NODESET);
            for (int i = 0; i < list.getLength(); i++) {
                //5.遍历输出相应的结果
                Element element = (Element) list.item(i);
                System.out.println(element.getElementsByTagName("title").item(0).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  XMLStreamWriter
     *  输出xml
     */
    @Test
    public void test07(){
        try {
            XMLStreamWriter xsw = XMLOutputFactory.newFactory().createXMLStreamWriter(System.out);
            xsw.writeStartDocument("UTF-8","1.0");
            xsw.writeEndDocument();
            String ns = "http://11:dd";
            xsw.writeStartElement("ns","person",ns);
            xsw.writeStartElement("id");
            xsw.writeCharacters("1");
            xsw.writeEndElement();
            xsw.writeEndElement();
            xsw.flush();
            xsw.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用Transformer更新节点信息
     */
    @Test
    public void test08() {
        InputStream is = null;
        try {
            is = StaxTest.class.getResourceAsStream("books.xml");
            //创建文档处理对象
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //通过DocumentBuilder创建doc的文档对象
            Document doc = db.parse(is);
            //创建XPath
            XPath xpath = XPathFactory.newInstance().newXPath();
            Transformer tran = TransformerFactory.newInstance().newTransformer();
            tran.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
            tran.setOutputProperty(OutputKeys.INDENT, "yes");
            //第一个参数就是xpath,第二参数就是文档
            NodeList list = (NodeList)xpath.evaluate("//book[title='Learning XML']", doc,XPathConstants.NODESET);
            //获取price节点
            Element be = (Element)list.item(0);
            Element e = (Element)(be.getElementsByTagName("price").item(0));
            e.setTextContent("333.9");
            Result result = new StreamResult(System.out);
            //通过tranformer修改节点
            tran.transform(new DOMSource(doc), result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(is!=null){ is.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
