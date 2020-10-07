package xyz.zelamkin.MFAN.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
public class FormBean {
	public static Object makeBean(HttpServletRequest request,Class<?> clazz){
		Object result = null;
	     try {
			result=clazz.newInstance();
			Field [] fields = clazz.getDeclaredFields();
			for(Field field:fields){
				field.setAccessible(true);
				String value=request.getParameter(field.getName());
				if(null!=value){
					switch(field.getType().getName()){
					case "java.lang.String":
						field.set(result, (String)value);
						break;
					case "int":
						int i = Integer.parseInt(value);
						field.setInt(result, i);
						break;
					case "double":
						double d = Double.parseDouble(value);
						field.setDouble(result, d);
						break;
					case "byte":
						byte b = Byte.parseByte(value);
						field.setByte(result, b);
						break;
					case "long":
						long l = Long.parseLong(value);
						field.setDouble(result, l);
						break;
					case "Integer":
						Integer I = Integer.parseInt(value);
						field.setInt(result, I);
						break;
					}
				}
			}
		
		} catch (InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		
			
		return result;
	}
	


	
}
