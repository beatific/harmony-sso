package org.beatific.harmony.sso.properties;

public class PropertiesNotFoundException extends RuntimeException {
	
	public PropertiesNotFoundException() {
		super();
	}
	
	public PropertiesNotFoundException(Exception ex) {
		super(ex);
	}
	
	public PropertiesNotFoundException(String message) {
		super(message);
	}
	
	public PropertiesNotFoundException(String message, Exception ex) {
		super(message, ex);
	}
}
