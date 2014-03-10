package org.beatific.harmony.sso.repository;


public enum RepositoryKinds {

	session, user;
	
	public String getCacheName() {
		
		switch(this) {
			case session : return "session";
			case user : return "user";
			default : return null;
		}
	}
	
	public static RepositoryKinds getRepositoryKind(String repositoryName) {
		
		switch(repositoryName) {
			case "session" : return session;
			case "user" : return user;
			default : return null;
		}
	}

	public static RepositoryKinds getRepositoryKinds(String className) {
		
		switch(className) {
			case "org.beatific.harmony.sso.repository.session.SessionRepository" : return session;
			case "org.beatific.harmony.sso.repository.duplicationuser.UserRepository" : return user;
			default : return null;
		}
	}
}
