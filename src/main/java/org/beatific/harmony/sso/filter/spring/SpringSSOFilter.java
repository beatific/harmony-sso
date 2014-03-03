package org.beatific.harmony.sso.filter.spring;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beatific.harmony.sso.agent.SSOAgent;
import org.springframework.web.filter.OncePerRequestFilter;

public class SpringSSOFilter extends OncePerRequestFilter{

//	private Lookup lookup;
	private SSOAgent ssoAgent;
//	public String contextResource;
	
	public void setAgent(SSOAgent ssoAgent) {
		this.ssoAgent = ssoAgent;
	}
	
	@Override
	protected void initFilterBean() throws ServletException {
		
//		try {
//			lookup = Lookup.getInstance();
//			lookup.setAgent(ssoAgent);
//			lookup.init(contextResource);
//			ssoAgent = lookup.lookupSSOAgent();
//		} catch (Exception e) {
//			throw new ServletException(e);
//		}
	}
		
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if(ssoAgent.certificate(request, response)) {
			filterChain.doFilter(request, response);
			return;
		}
	}
	

}