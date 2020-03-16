package com.wz.service.xml.apply;

import org.junit.Test;
import org.omg.CORBA.MARSHAL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * @author jamesbean
 */
public class JaxbTest {

    /**
     * java转xml
     */
    @Test
    public void test01(){
        try {
            JAXBContext ctx = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = ctx.createMarshaller();
            Classroom classroom = new Classroom(1,"计算机",2015);
            Student student = new Student(1,"琳","女",classroom);
//            Student student = new Student();
            marshaller.marshal(student,System.out);
//            <?xml version="1.0" encoding="UTF-8" standalone="yes"?><student><age>女</age><classroom><grade>2015</grade><id>1</id><name>计算机</name></classroom><id>1</id><name>琳</name></student>
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * xml转java
     */
    @Test
    public void test02(){
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>女</age><classroom><grade>2015</grade><id>1</id><name>计算机</name></classroom><id>1</id><name>琳</name></student>";
        try {
            JAXBContext ctx = JAXBContext.newInstance(Student.class);
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            Student student = (Student)unmarshaller.unmarshal(new StringReader(xml));
            System.out.println(student);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
