package org.beatific.harmony.sso.policy;

import javax.servlet.http.HttpServletRequest;

import org.beatific.harmony.sso.session.Session;

public abstract class AbstractPolicy<E, O> implements Policy{
	
	protected Session decide(Session session, HttpServletRequest request, PolicyExtractor<E> extractor) {
		
		session.setAttribute(this.getClass().getName(), extractor.extract(request));
		return session;
	}
	
	@SuppressWarnings("unchecked")
	protected boolean enforce(Session session, HttpServletRequest request, PolicyExtractor<O> extractor) {
		
		if(session == null)return false;
		E element = (E)session.getAttribute(this.getClass().getName());
		O object = extractor.extract(request);
		
		return matches(element, object);
		
	}
	
	public abstract Session decide(Session session, HttpServletRequest request);
	public abstract boolean enforce(Session session, HttpServletRequest request);
	protected abstract boolean matches(E element, O object);
	
}
