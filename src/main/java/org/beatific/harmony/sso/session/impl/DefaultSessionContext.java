package org.beatific.harmony.sso.session.impl;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.beatific.harmony.sso.policy.Policies;
import org.beatific.harmony.sso.repository.Repositories;
import org.beatific.harmony.sso.repository.RepositoryKinds;
import org.beatific.harmony.sso.session.Session;
import org.beatific.harmony.sso.session.SessionContext;
import org.beatific.harmony.sso.session.SessionIdGenerator;
import org.beatific.harmony.sso.session.SessionValidator;

public class DefaultSessionContext implements SessionContext {

	private Policies policies;
	private Repositories repositories;
	
	private SessionValidator validator;
	private int idGenerateCount = 1;
	private Random random = new Random();
	
	public void setPolicies(Policies policies) {
		this.policies = policies;
	}
	
	public void setRepositories(Repositories repositories) {
		this.repositories = repositories;
	}
	
	public void setIdGenerateCount(int idGenerateCount) {
		this.idGenerateCount = idGenerateCount;
	}
	
	public void setSessionValidator(SessionValidator validator) {
		this.validator = validator;
	}
	
	public void invalidate(Session session) {
		
		repositories.remove(session);
	}
	
	public Session getSession(String sessionId, HttpServletRequest request) {
		
		Session session =  (Session)repositories.get(RepositoryKinds.session, sessionId);
		
		if(!policies.enforce(session, request)) return null;

		session.updateLastAccessedTime();
		
		if(session != null && this.idGenerateCount != 0  &&  random.nextInt() % this.idGenerateCount == 0) {
			String generatedId = SessionIdGenerator.generate();
			
			repositories.remove(session);
			
			session.setId(generatedId);
			
			repositories.add(session);
		}
		
		 session = validator.validate(session, this);
		
		return session;
	}
	
	public String newInstace(HttpServletRequest request) {
		return newInstace(request, null);
	}
	
	public String newInstace(HttpServletRequest request, String userId) {
		
		String sessionId = SessionIdGenerator.generate();
		Session session = new Session(sessionId);
		session.setUserId(userId);
		session.setAttribute("userId", userId);
		session = policies.decide(session, request);
		
		repositories.add(session);
		
		return sessionId;
		
	}
	
	public boolean isDuplicationLogin(String userId) {
		return repositories.get(RepositoryKinds.user, userId) != null;
	}
	
}
