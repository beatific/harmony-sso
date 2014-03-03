package org.beatific.harmony.sso.repository;

public interface Repository<T> {

	public void add(String key, T value);
	public T get(String key);
	public void remove(String key);
}
