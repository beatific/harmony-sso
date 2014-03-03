package org.beatific.harmony.sso.context;

public interface SSOContext {

	public void init();
	public Object lookup(String name);
}
