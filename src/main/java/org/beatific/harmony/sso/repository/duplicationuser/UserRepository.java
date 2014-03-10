package org.beatific.harmony.sso.repository.duplicationuser;

import org.beatific.harmony.sso.repository.AbstractRepository;
import org.beatific.harmony.sso.repository.RepositoryKinds;
import org.beatific.harmony.sso.repository.trigger.Trigger;
import org.beatific.harmony.sso.session.Session;


public class UserRepository extends AbstractRepository {

	public UserRepository() {
		setRepositoryKinds(RepositoryKinds.user);
	}

	@Override
	public String getKey(Session session) {
		if(session != null) return session.getUserId();
		return null;
	}

	@Override
	public Object getValue(Session session) {
		if(session != null) return session.getId();
		return null;
	}

	@Override
	public Object[] checkRemove(Session session, Trigger trigger) {
		Object sessionId = cache.get(getKey(session));
		Session newSession = new Session((String)sessionId);
		if(sessionId != null) {
			Object[] result = new Object[2];
			result[0] = newSession;
			result[1] = trigger;
			return result;
		}
		return null;
	}

	@Override
	public void addToRepository(String key, Object value) {
		System.out.println("UserRepository : addToRepository key[" + key + "] value [" + value + "]");
	}

	@Override
	public void removeFromRepository(String key) {
		System.out.println("UserRepository : removeFromRepository key [" + key + "]");
		
	}

	@Override
	public Object getFromRepository(String key) {
		System.out.println("UserRepository : getFromRepository key [" + key + "]");
		return null;
	}
	
}
