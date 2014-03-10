package org.beatific.harmony.sso.session;

import org.beatific.harmony.sso.properties.Properties;
import org.beatific.harmony.sso.properties.PropertiesNotFoundException;

public class SessionValidator {
	
	public final static String LIMITED_VALIDATE_TIME = "session_limit_time";
	private Properties properties = null;

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public Session validate(Session session, SessionContext context) {
		
		if(session == null) return null;
		
		long timeMillis = System.currentTimeMillis();
		if(timeMillis - session.getLastAccessedTime()  > getLimitedValidateTime()) {
			session.invalidate(context);
			return null;
		} 
		return session;
	}
	
	private long getLimitedValidateTime() {
		
		if (properties == null) {
			throw new PropertiesNotFoundException("SSO Properties is not found");
		} 
		
		if (properties.get(LIMITED_VALIDATE_TIME) == null) {
			return 3600000;
		} 
		
		return Long.parseLong(properties.getProperty(LIMITED_VALIDATE_TIME)) * 1000;
	}
	
	public boolean isValidate(Session session) {
		
		long timeMillis = System.currentTimeMillis();
		return (timeMillis - session.getLastAccessedTime()  <= getLimitedValidateTime());
	}
}
