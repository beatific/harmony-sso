package org.beatific.harmony.sso.properties.spring;

import java.util.Locale;
import org.beatific.harmony.sso.properties.Properties;
import org.springframework.context.MessageSource;

public class SpringProperties implements Properties {

	private MessageSource properties;
	
    public SpringProperties()
    {
    }

    public void setMessageSource(MessageSource properties)
    {
        this.properties = properties;
    }

    public String getProperty(String key)
    {
        return properties.getMessage(key, null, Locale.getDefault());
    }

    public Object get(String key)
    {
        return properties.getMessage(key, null, Locale.getDefault());
    }
    
}