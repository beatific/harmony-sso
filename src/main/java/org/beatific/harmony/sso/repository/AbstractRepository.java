package org.beatific.harmony.sso.repository;

import org.beatific.harmony.sso.cache.Cache;
import org.beatific.harmony.sso.cache.CacheManager;
import org.beatific.harmony.sso.repository.trigger.Trigger;
import org.beatific.harmony.sso.session.Session;

public abstract class AbstractRepository implements Repository {
	
	private CacheManager cacheManager;
	protected Cache cache = null;
	private RepositoryKinds kind;

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
		if(kind == null) new RepositoryKindsNullPointException("The Kinds Of Repository Not Decided!!");
		cache = this.cacheManager.getCache(kind.getCacheName());
	}
	
	public void setRepositoryKinds(RepositoryKinds kind) {
		this.kind = kind;
	}
	
	public String getRepositoryKinds() {
		return kind.toString();
	}
	
	public void add(Session session, Trigger trigger) {
		
		trigger.pull(session, this);
		cache.put(getKey(session), getValue(session));
	}
	
	public Object get(String key) {
		
		Object value = cache.get(key);
		
		if(value == null) {
			value = getFromRepository(key);
			if(value != null)cache.put(key, value);
		}
		
		return value;
	}
	
	public void remove(Session session, Trigger trigger) {
		trigger.pull(session, this);
		cache.remove(getKey(session));
	}
	
	public void evict(Session session, Trigger trigger) {
		trigger.pull(session, this);
		cache.evict(getKey(session));
	}
	
	public Object[] checkAdd(Session session, Trigger trigger) {
		return null;
	}
	
	public Object[] checkRemove(Session session, Trigger trigger) {
		return null;
	}
	
	public Object[] checkEvict(Session session, Trigger trigger) {
		return null;
	}
 
	public void addRepository(Session session) {
		
		addToRepository(getKey(session), getValue(session));
	}
	
	public void removeRepository(Session session) {
		
		removeFromRepository(getKey(session));
	}
	
	public abstract void addToRepository(String key, Object value);
	public abstract void removeFromRepository(String key);
	public abstract Object getFromRepository(String key);
	public abstract String getKey(Session session);
	public abstract Object getValue(Session session);
	
}
