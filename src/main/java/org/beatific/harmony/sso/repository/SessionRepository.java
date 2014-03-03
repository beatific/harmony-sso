package org.beatific.harmony.sso.repository;

import org.beatific.harmony.sso.session.Session;

public class SessionRepository extends AbstractRepository<Session>{

	public SessionRepository() {
		setRepositoryKinds(RepositoryKinds.session);
	}
	
	@Override
	public void addToRepository(String key, Object value) {
		
	}

	@Override
	public Session getFromRepository(String key) {
		return null;
	}

	@Override
	public void removeFromRepository(String key) {
		
	}
	
}
