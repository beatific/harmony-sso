package org.beatific.harmony.sso.policy.security.fingerprint;

import java.io.Serializable;

public class FingerPrintElement implements Serializable{

	private String userAgent;
	private String ipAddr;
	
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
}
