package com.success.web.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class SerializeUtil {

	public static Object unSerialize(String serStr) throws Exception {
		String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream); 
        Object obj = objectInputStream.readObject();
        
        objectInputStream.close();
        byteArrayInputStream.close();
        return obj;
    }
	
}
