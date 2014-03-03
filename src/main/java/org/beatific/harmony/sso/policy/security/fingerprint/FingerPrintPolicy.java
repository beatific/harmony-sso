package org.beatific.harmony.sso.policy.security.fingerprint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.beatific.harmony.sso.policy.AbstractPolicy;
import org.beatific.harmony.sso.policy.Policy;
import org.beatific.harmony.sso.policy.PolicyExtractor;
import org.beatific.harmony.sso.policy.security.SecureObject;
import org.beatific.harmony.sso.session.Session;

public class FingerPrintPolicy implements Policy {
		
	private Policy policy = null;
    private final String IP = "ipAddr";
    private final String USER_AGENT = "userAgent";
	
	public FingerPrintPolicy()
    {
		policy = new AbstractPolicy<FingerPrintElement, SecureObject>() {
			
			@Override
	 public boolean enforce(Session session, HttpServletRequest source)
     {
         return enforce(session, source, new PolicyExtractor<SecureObject>() {

				@Override
				protected Object extractValue(HttpServletRequest request, String fieldName) {
					if(IP.equals(fieldName)) {
						return getClientIpAddr(request);
					} else if(USER_AGENT.equals(fieldName)){
						return getUserAgent(request);
					}
					return null;
				}
				
			});
     }

			@Override
			protected boolean matches(FingerPrintElement element, SecureObject object) {
				
				Method[] methods = element.getClass().getMethods();
				for(Method method : methods) {
					if(method.getName().startsWith("get")) {
						try {
							Method getter = object.getClass().getMethod(method.getName(), null);
							return getter.invoke(object, null).equals(method.invoke(element, null));
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException 
								| IllegalArgumentException | InvocationTargetException e) {
							
						}
					}
				}
				return false;
			}
			
			@Override
			public Session decide(Session session, HttpServletRequest request) {
				return decide(session, request,  new PolicyExtractor<FingerPrintElement>() {

					@Override
					protected Object extractValue(HttpServletRequest request, String fieldName) {
						if(IP.equals(fieldName)) {
							return getClientIpAddr(request);
						} else if(USER_AGENT.equals(fieldName)){
							return getUserAgent(request);
						}
						return null;
					}
					
				});
			}
			
			private String getClientIpAddr(HttpServletRequest request) {  
		        String ip = request.getHeader("X-Forwarded-For");
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		            ip = request.getHeader("Proxy-Client-IP");  
		        }  
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		            ip = request.getHeader("WL-Proxy-Client-IP");  
		        }  
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		            ip = request.getHeader("HTTP_CLIENT_IP");  
		        }  
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
		        }  
		        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		            ip = request.getRemoteAddr();  
		        }  
		        return ip;  
		    }
			
			private String getUserAgent(HttpServletRequest request) {
				return request.getHeader("User-Agent");
		    }

		};
	}
	
	public boolean enforce(Session session, HttpServletRequest request) {
		return policy.enforce(session, request);
	}
	
	public Session decide(Session session, HttpServletRequest request) {
		return policy.decide(session, request);
	}
}
