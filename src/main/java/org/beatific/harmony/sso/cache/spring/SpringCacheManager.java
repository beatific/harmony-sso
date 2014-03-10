package org.beatific.harmony.sso.cache.spring;

import java.util.Collection;

import org.beatific.harmony.sso.cache.Cache;
import org.beatific.harmony.sso.cache.CacheManager;

import com.hazelcast.core.IMap;
import com.hazelcast.spring.cache.HazelcastCacheManager;

public class SpringCacheManager implements CacheManager {
		
		private HazelcastCacheManager cacheManager;
		
		public void setCacheManager(HazelcastCacheManager cacheManager) {
			this.cacheManager = cacheManager;
		}
		
		public Cache getCache(String name) {
			IMap map;
			org.springframework.cache.Cache cache = cacheManager.getCache(name);
			return new SpringCache(cache);
		}

		public Collection<String> getCacheNames() {
			return null;
		}

	}
