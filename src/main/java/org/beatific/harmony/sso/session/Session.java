package org.beatific.harmony.sso.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Session implements Serializable {

	private Map<Object, Object> map;
	private long creationtime;
	private long lastAccessedTime;
	private String id;
	private String userId;
	private int interval;
	private boolean newSession = true;
	
	public Session(String id) {
		this.map = new HashMap();
		this.id = id;
		this.lastAccessedTime = System.currentTimeMillis();
		this.creationtime = System.currentTimeMillis();
	}
	
	public long getCreationTime() {
		
		return this.creationtime;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		
		return this.id;
	}
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getLastAccessedTime() {
		
		return this.lastAccessedTime;
	}

	public void setMaxInactiveInterval(int interval) {
		this.interval = interval;
		
	}

	public int getMaxInactiveInterval() {
		
		return this.interval;
	}

	public Object getAttribute(String name) {
		return map.get(name);
	}

	public Object[] getAttributeNames() {
		return map.keySet().toArray();
	}

	public void setAttribute(String name, Object value) {
		map.put(name, value);
		
	}

	public void removeAttribute(String name) {
		map.remove(name);
		
	}

	public void invalidate(SessionContext context) {
		
		context.invalidate(id, userId);
		
		id = null;
		userId = null;
		map.clear();
	}

	public boolean isNew() {
		return this.newSession;
	}

	public void updateLastAccessedTime() {
		this.lastAccessedTime = System.currentTimeMillis();
	}	
}
