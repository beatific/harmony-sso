package org.beatific.harmony.sso.repository.trigger;


public enum MethodKinds {

	C, R, D, E;

	public String toString() {
		
		switch(this) {
		case C : return "C";
		case R : return "R";
		case D : return "D";
		case E : return "E";
			default : return null;
		}
	}

	public static MethodKinds getMethodKinds(String methodName) {
		
		switch(methodName) {
			case "add" : return C;
			case "get" : return R;
			case "remove" : return D;
			case "evict" : return E;
			default : return null;
		}
	}
	
	public static String getMethodName(String methodKinds) {
		
		switch(methodKinds) {
			case "C" : return "add";
			case "R" : return "get";
			case "D" : return "remove";
			case "E" : return "evict";
			default : return null;
		}
	}
	
}
