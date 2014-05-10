package com.util.common.xml;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;

public class JAXBUtils {
	
	public static void marshal(Object obj, OutputStream outStream) {
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshal = context.createMarshaller();
			marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshal.marshal(obj, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object unmarshal(InputStream inputStream, Class<?> clz) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clz);
		Unmarshaller unmarshal = context.createUnmarshaller();
		return unmarshal.unmarshal(inputStream);
	}
}
