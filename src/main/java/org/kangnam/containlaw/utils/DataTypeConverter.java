package org.kangnam.containlaw.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class DataTypeConverter {
    public static <T> T xmlToObject(String xml, Class<T> _class) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(_class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return _class.cast(jaxbUnmarshaller.unmarshal(new StringReader(xml)));
        } catch (Exception e) {
            System.out.println("xml -> "+_class+") 변환 실패");
            return null;
        }
    }

}
