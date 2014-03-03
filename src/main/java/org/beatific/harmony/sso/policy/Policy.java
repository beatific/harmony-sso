package org.beatific.harmony.sso.policy;

import javax.servlet.http.HttpServletRequest;

import org.beatific.harmony.sso.session.Session;

public interface Policy {

	public Session decide(Session session, HttpServletRequest request);
	public boolean enforce(Session session, HttpServletRequest request);
	
}
