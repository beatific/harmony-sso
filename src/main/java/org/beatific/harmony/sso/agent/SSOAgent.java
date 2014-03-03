package org.beatific.harmony.sso.agent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beatific.harmony.sso.session.Session;

public interface SSOAgent {

	public void ssologin(HttpServletRequest request, HttpServletResponse response);
	public void ssologin(HttpServletRequest request, HttpServletResponse response, String id);
	public Session getSession(HttpServletRequest request, HttpServletResponse response);
	public boolean certificate(HttpServletRequest request, HttpServletResponse response);
	public boolean isDuplicationLogin(String userId);
}
