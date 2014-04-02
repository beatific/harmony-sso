package org.beatific.harmony.sso.repository.trigger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {

	public static Object invoke(Object object, String methodName, Object[] params) {
		
		Class<?> clazz = object.getClass();
		try {
			Method method = clazz.getMethod(methodName, getParamTypes(params));
			return method.invoke(object, params);
			
		} catch (NoSuchMethodException | SecurityException |IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		}
		
		return null;
	}
	
	private static Class<?>[] getParamTypes(Object[] params) {
		Class<?>[] classes = new Class<?>[params.length];
		
		int i = 0;
		for(Object param : params) {
			classes[i++] = param.getClass();  
		}
		
		return classes;
	}
}
