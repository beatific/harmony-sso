package org.beatific.harmony.sso.repository;

public enum RepositoryKinds {

	session, user;
	
	public String getCacheName() {
		
		switch(this) {
			case session : return "session";
			case user : return "userId";
			default : return null;
		}
	}
}
