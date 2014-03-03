package org.beatific.harmony.sso.cache;

import java.util.Collection;

import com.hazelcast.spring.cache.HazelcastCacheManager;

public interface CacheManager {
	
	public void setCacheManager(HazelcastCacheManager cacheManager);
	
	public Cache getCache(String s);

	public Collection<String> getCacheNames();

}
