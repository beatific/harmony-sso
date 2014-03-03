package org.beatific.harmony.sso.policy;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeResolver {

	public static Class<?> getGenericTypeClass(Class<?> clazz, int index) {
		  Class<?> result =null;
	        Type type = clazz.getGenericSuperclass();

	        if(type instanceof ParameterizedType){
	             ParameterizedType pt =(ParameterizedType) type;
	             Type[] fieldArgTypes = pt.getActualTypeArguments();
	             result =(Class<?>) fieldArgTypes[index];
	       }
	       return result;
	}
}
