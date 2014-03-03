package org.beatific.harmony.sso.session.impl;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.beatific.harmony.sso.policy.Policies;
import org.beatific.harmony.sso.repository.Repository;
import org.beatific.harmony.sso.session.Session;
import org.beatific.harmony.sso.session.SessionContext;
import org.beatific.harmony.sso.session.SessionIdGenerator;
import org.beatific.harmony.sso.session.SessionValidator;

public class DefaultSessionContext implements SessionContext {

	private Repository<Session> sessionRepository;
	private Repository<String> userRepository;
	private Policies policies;
	
	private SessionValidator validator;
	private int idGenerateCount = 1;
	private Random random = new Random();
	
	public void setPolicies(Policies policies) {
		this.policies = policies;
	}
	
	public void setSessionRepository(Repository<Session> repository) {
		sessionRepository = repository;
	}
	
	public void setUserRepository(Repository<String> repository) {
		userRepository = repository;
	}
	
	public void setIdGenerateCount(int idGenerateCount) {
		this.idGenerateCount = idGenerateCount;
	}
	
	public void setSessionValidator(SessionValidator validator) {
		this.validator = validator;
	}
	
	public void invalidate(String sessionId) {
		invalidate(sessionId, null);
	}
	
	public void invalidate(String sessionId, String userId) {
		
		sessionRepository.remove(sessionId);
		if(userId != null)userRepository.remove(userId);
	}
	
	public Session getSession(String sessionId, HttpServletRequest request) {
		
		Session session =  sessionRepository.get(sessionId);
		
		if(!policies.enforce(session, request)) return null;
		
		if(session != null && this.idGenerateCount != 0  &&  random.nextInt() % this.idGenerateCount == 0) {
			String generatedId = SessionIdGenerator.generate();
			session.setId(generatedId);
			sessionRepository.remove(sessionId);
			sessionRepository.add(generatedId, session);
			
			
			String userId = session.getUserId();
			if(userId != null) {
				userRepository.remove(userId);
				userRepository.add(userId, generatedId);
			}
		}
		
		validator.validate(session, this);
		return session;
	}
	
	public String newInstace(HttpServletRequest request) {
		return newInstace(request, null);
	}
	
	public String newInstace(HttpServletRequest request, String userId) {
		
		String sessionId = SessionIdGenerator.generate();
		Session session = new Session(sessionId);
		
		session = policies.decide(session, request);
		
		if(userId != null) {
			session.setUserId(userId);
			String storedSessionId = userRepository.get(userId);
			if(storedSessionId != null) {
				userRepository.remove(userId);
				sessionRepository.remove(storedSessionId);
			}
			
			userRepository.add(userId, sessionId);
		}
		
		sessionRepository.add(sessionId, session);
		
		return sessionId;
		
	}
	
	public boolean isDuplicationLogin(String userId) {
		return (userRepository.get(userId) != null);
	}
	
}
