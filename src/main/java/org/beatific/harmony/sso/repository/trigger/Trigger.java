package org.beatific.harmony.sso.repository.trigger;

import java.util.Map;
import java.util.Properties;

import org.beatific.harmony.sso.repository.Repository;
import org.beatific.harmony.sso.repository.RepositoryKinds;
import org.beatific.harmony.sso.session.Session;


public class Trigger {
	
	private Properties relations = null;
	private Map<RepositoryKinds, Repository> repositories;
	
	private RepositoryKinds convertRepository(String className) {
		return RepositoryKinds.getRepositoryKinds(className);
	}
	
	private MethodKinds convertMethod(String methodName) {
		return MethodKinds.getMethodKinds(methodName);
	}
	
	private Repository getRepository(String repositoryKind) {
		return repositories.get(RepositoryKinds.getRepositoryKind(repositoryKind));
	}
	
	private Repository getRepository(RepositoryKinds repositoryKind) {
		return repositories.get(repositoryKind);
	}
	
	private void dragTrigger(String className, String methodName, Session session) {
		
		if(relations == null) return;
		
		String relation = relations.getProperty(new StringBuffer(convertRepository(className).getCacheName())
							.append(":").append(convertMethod((methodName).toString())).toString());
		
		if(relation == null) return;
		
		Repository parent = getRepository(convertRepository(className));
		String[] relations = relation.split(":");
		Repository child = getRepository(relations[0]);
		String repositoryMethodName = MethodKinds.getMethodName(relations[1]);
		
		Object[] params = new Object[1];
		params[0] = session;
		
		Object[] result = check(child, repositoryMethodName, session);
		if(result != null)invoke(parent, child, repositoryMethodName, session, result);
	}
	
	public void setRepositories(Map<RepositoryKinds, Repository> repositories) {
		this.repositories = repositories;
	}

	public void setRelations(Properties relations) {
		this.relations = relations;
	}
	
	public void pull(Session session, Object repository) {
		
		StackTraceElement ste = new Throwable().getStackTrace()[1];
		String methodName = ste.getMethodName();
		String className = repository.getClass().getName();
		dragTrigger(className, methodName, session);
	}
	
	private Object[] check(Repository repository, String methodName, Session session) {
		
		String checkMethodName = new StringBuffer("check").append(methodName.substring(0, 1).toUpperCase()).append(methodName.substring(1)).toString();
		
		Object[] params = new Object[2];
		params[0] = session;
		params[1] = this;
		return (Object[]) ReflectionUtil.invoke(repository, checkMethodName, params);
	}
	
	private void invoke(Repository parent, Repository child, String methodName, Session session, Object[] result) {
		
		Object[] params = null;
		if(parent == child) params = getParams(session);
		else params = result;
		
		ReflectionUtil.invoke(parent, methodName, params);
	}
		
	private Object[] getParams(Session session) {
		Object[] params = new Object[2];
		params[0] = session;
		params[1] = this;
		return params;
	}

}
