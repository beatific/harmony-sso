package org.beatific.harmony.sso.cache.spring;

import org.beatific.harmony.sso.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;


public class SpringCache implements Cache{

	private org.springframework.cache.Cache cache;
	
	public SpringCache(org.springframework.cache.Cache cache) {
		this.cache = cache;  
	}

	public void clear() {
		cache.clear();
	}

	public void evict(Object arg0) {
		cache.evict(arg0);
		
	}
	
	public Object get(Object arg0) {
		ValueWrapper vw = cache.get(arg0);
		if(vw == null)return null;
		return vw.get();
	}
	
	public void put(Object arg0, Object arg1) {
		cache.put(arg0, arg1);
	}


}
