package org.beatific.harmony.sso.repository.session;

import org.beatific.harmony.sso.repository.AbstractRepository;
import org.beatific.harmony.sso.repository.RepositoryKinds;
import org.beatific.harmony.sso.session.Session;

public class SessionRepository extends AbstractRepository{

	public SessionRepository() {
		setRepositoryKinds(RepositoryKinds.session);
	}

	@Override
	public String getKey(Session session) {
		return session.getId();
	}

	@Override
	public Object getValue(Session session) {
		return session;
	}

	@Override
	public void addToRepository(String key, Object value) {
		// Merge로 만들어야 한다.
		System.out.println("SessionRepository : addToRepository key[" + key + "] value [" + value + "]");
		
	}

	@Override
	public void removeFromRepository(String key) {
		System.out.println("SessionRepository : removeFromRepository key [" + key + "]");
	}

	@Override
	public Object getFromRepository(String key) {
		System.out.println("SessionRepository : getFromRepository key [" + key + "]");
		return null;
	}
	
}
