package org.beatific.harmony.sso.properties;

public abstract class AbstractPropsObject {
	
	protected Properties props;
	
	public void setProperties(Properties properties) {
		this.props = properties;
		loadEnvironments(props);
	}
	
	abstract protected void loadEnvironments(Properties props);
}
