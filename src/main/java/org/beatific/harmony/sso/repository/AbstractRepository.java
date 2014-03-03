package org.beatific.harmony.sso.repository;

import org.beatific.harmony.sso.cache.Cache;
import org.beatific.harmony.sso.cache.CacheManager;

public abstract class AbstractRepository<T> implements Repository<T>{
	
	protected CacheManager cacheManager;
	private Cache cache = null;
	private RepositoryKinds kind;

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
		if(kind == null) new RepositoryKindsNullPointException("The Kinds Of Repository Not Decided!!");
		cache = cacheManager.getCache(kind.getCacheName());
	}
	
	protected void setRepositoryKinds(RepositoryKinds kind) {
		this.kind = kind;
	}
		
	public void add(String key, T value) {
		addToRepository(key, value);
		cache.put(key, value);
	}
	
	protected abstract void addToRepository(String key, Object value);
	
	@SuppressWarnings("unchecked")
	public T get(String key) {
		T value = (T)cache.get(key);
		
		if(value==null) {
			value = getFromRepository(key);
			if(value==null)cache.put(key, value);
		}
		
		return value;
	}
	
	protected abstract T getFromRepository(String key);
	
	public void remove(String key) {
		removeFromRepository(key);
		cache.evict(key);
	}
	
	protected abstract void removeFromRepository(String key);
}
