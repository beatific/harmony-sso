package org.beatific.harmony.sso.repository;

public class RepositoryKindsNullPointException extends RuntimeException {
	
	public RepositoryKindsNullPointException() {
		super();
	}
	
	public RepositoryKindsNullPointException(Exception ex) {
		super(ex);
	}
	
	public RepositoryKindsNullPointException(String message) {
		super(message);
	}
	
	public RepositoryKindsNullPointException(String message, Exception ex) {
		super(message, ex);
	}
}
