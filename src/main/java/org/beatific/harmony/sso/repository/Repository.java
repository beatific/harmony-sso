package org.beatific.harmony.sso.repository;

import org.beatific.harmony.sso.repository.trigger.Trigger;
import org.beatific.harmony.sso.session.Session;

public interface Repository {

	public void add(Session session, Trigger trigger);
	public Object get(String key);
	public void remove(Session session, Trigger trigger);
	public String getRepositoryKinds();
	public void evict(Session session, Trigger trigger);
	
	public void addRepository(Session session);
	public void removeRepository(Session session);
}
