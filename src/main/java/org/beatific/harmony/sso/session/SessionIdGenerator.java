package org.beatific.harmony.sso.session;

import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;

public class SessionIdGenerator {

	public static synchronized String generate() {
		
		DateTime time = DateTime.now();
		return deriveKey(time);

	}
	
	private static String deriveKey(DateTime time) {

		return BCrypt.hashpw(time.toString(), BCrypt.gensalt(4));
	}
}
