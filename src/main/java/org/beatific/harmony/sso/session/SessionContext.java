package org.beatific.harmony.sso.session;

import javax.servlet.http.HttpServletRequest;

public interface SessionContext {

	public void invalidate(Session session);
	public Session getSession(String sessionId, HttpServletRequest request);
	public String newInstace(HttpServletRequest request, String userId);
	public String newInstace(HttpServletRequest request);
	public boolean isDuplicationLogin(String userId);
}
