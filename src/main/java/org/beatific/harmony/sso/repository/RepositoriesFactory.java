package org.beatific.harmony.sso.repository;

public class RepositoriesFactory {

	private static Repositories repositories;
	
	public static Repositories getInstance() {
		return repositories;
	}
	
	public static void setInstance(Repositories repos) {
		repositories = repos;
	}
}
