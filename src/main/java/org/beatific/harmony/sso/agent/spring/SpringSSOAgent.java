package org.beatific.harmony.sso.agent.spring;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beatific.harmony.sso.agent.SSOAgent;
import org.beatific.harmony.sso.properties.AbstractPropsObject;
import org.beatific.harmony.sso.properties.Properties;
import org.beatific.harmony.sso.session.Session;
import org.beatific.harmony.sso.session.SessionContext;

public class SpringSSOAgent extends AbstractPropsObject implements SSOAgent {

	private SessionContext context;
	
	private List<String> ignoreURIs;
	private String loginURI;
	private String logoutURI;
	
	private static final String IGNORE_URI = "session_ignore_uri";
	private static final String LOGIN_URI = "login_uri";
	private static final String LOGOUT_URI = "logout_uri";
	private static final String SESSIONID = "HARMONY_SESSIONID";
	private static final String DOMAIN = "domain";

	public void setSessionContext(SessionContext context) {
		this.context = context;
	}
	
	public Session getSession(HttpServletRequest request, HttpServletResponse response) {
		Session session = context.getSession(getCookie(request, SESSIONID), request);
		if(session != null)setCookie(response, SESSIONID, session.getId());
		return session;
	}

	@Override
	protected void loadEnvironments(Properties props) {
		String ignoreUrl = props.getProperty(IGNORE_URI);
		if(ignoreUrl != null)ignoreURIs = Arrays.asList(ignoreUrl.split(","));
		
		loginURI = props.getProperty(LOGIN_URI);
		logoutURI = props.getProperty(LOGOUT_URI);
	}
	
	private String getCookie(HttpServletRequest request, String name) {
		
		Cookie[] cookies = request.getCookies();
		
		for (int idx = 0;cookies != null && idx < cookies.length;idx++) {
		    if (cookies[idx].getName().equals(name)) {
		    	return cookies[idx].getValue();
		    }
		}
		
		return null;
	}
	
	private void setCookie(HttpServletResponse response, String name, String value)
	{
		Cookie cookie = null;
		
		cookie = new Cookie(name, value);
		cookie.setDomain(props.getProperty(DOMAIN));
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	private void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		for(int i =0; i < cookies.length; i++) {
			if(cookies[i].getName().equals(name)) {
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) {

		Session session = getSession(request, response);
		if(session != null)session.invalidate(context);
		removeCookie(request, response, SESSIONID);
	}
	
	public void ssologin(HttpServletRequest request, HttpServletResponse response) {
		ssologin(request, response, null);
	}
	
	public void ssologin(HttpServletRequest request, HttpServletResponse response, String userId) {
		
		String sessionId = context.newInstace(request, userId);
		setCookie(response, SESSIONID, sessionId);
	}
	
	public boolean isDuplicationLogin(String userId) {
		return context.isDuplicationLogin(userId);
	}

	public boolean certificate(HttpServletRequest request, HttpServletResponse response) {

		if(ignoreURIs.contains(request.getRequestURI()) || loginURI.equals(request.getRequestURI())) {
			return true;
		} 
		
		if(request.getRequestURI().equals(logoutURI)) {
			logout(request, response);
			return true;
		} else {
			Session session = getSession(request, response);
			if(session != null) return true;
		}
		return false;
	}
	
}
