package org.beatific.harmony.sso.cache;

public interface Cache {

	public void clear();

	public void evict(Object arg0);

	public Object get(Object arg0);

	public void put(Object arg0, Object arg1);

}
