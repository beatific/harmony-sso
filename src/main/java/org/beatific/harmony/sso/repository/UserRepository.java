package org.beatific.harmony.sso.repository;


public class UserRepository extends AbstractRepository<String>{

	public UserRepository() {
		setRepositoryKinds(RepositoryKinds.user);
	}
	
	@Override
	protected void addToRepository(String key, Object userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getFromRepository(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void removeFromRepository(String key) {
		// TODO Auto-generated method stub
		
	}
	
}
