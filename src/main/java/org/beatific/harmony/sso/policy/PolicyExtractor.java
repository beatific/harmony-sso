package org.beatific.harmony.sso.policy;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

public abstract class PolicyExtractor<T>
{

    public T extract(HttpServletRequest request)
    {
        return extract(request, null);
    }

    @SuppressWarnings("unchecked")
	public T extract(HttpServletRequest request, T destination)
    {
        Class<?> clazz = TypeResolver.getGenericTypeClass(getClass(), 0);
        
        try
        {
            if(destination == null)
                destination = (T)clazz.newInstance();
            
            Field fields[] = clazz.getDeclaredFields();
            
            for(Field field : fields) {
            	
                field.setAccessible(true);
                Object value = extractValue(request, field.getName());
                if(value != null)
                    field.set(destination, value);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return destination;
    }

    protected abstract Object extractValue(HttpServletRequest httpservletrequest, String s);
}
