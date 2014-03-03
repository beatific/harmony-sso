package org.beatific.harmony.sso.abstraction;

public class AbstractSSOException extends RuntimeException {
	
	public AbstractSSOException() {
		super();
	}
	
	public AbstractSSOException(Exception ex) {
		super(ex);
	}
	
	public AbstractSSOException(String message) {
		super(message);
	}
	
	public AbstractSSOException(String message, Exception ex) {
		super(message, ex);
	}
}
