package org.beatific.harmony.sso.cache;

import org.beatific.harmony.sso.repository.Repositories;
import org.beatific.harmony.sso.repository.RepositoriesFactory;
import org.beatific.harmony.sso.session.Session;
import org.beatific.harmony.sso.session.SessionValidator;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;

public class CacheEventListener implements EntryListener {
	
	private Repositories repositories = null;
	private SessionValidator validator = null;
	
	public void setSessionValidator(SessionValidator validator) {
		this.validator = validator;
	}
	
	public void entryAdded(EntryEvent event) {
		System.err.println("Added: " + event);
		
		if(repositories == null) {
			repositories = RepositoriesFactory.getInstance();
		}
		
	}

	public void entryRemoved(EntryEvent event) {
		System.err.println("Removed: " + event);
		
		if(repositories == null) {
			repositories = RepositoriesFactory.getInstance();
		}
		
		Session session = (Session)event.getValue();
		
		repositories.removeRepository(session);
	}

	public void entryUpdated(EntryEvent event) {
		System.err.println("Updated: " + event);
		
		if(repositories == null) {
			repositories = RepositoriesFactory.getInstance();
		}
	}

	public void entryEvicted(EntryEvent event) {
		System.err.println("Evicted: " + event);
		
		if(repositories == null) {
			repositories = RepositoriesFactory.getInstance();
		}
		
		Session session = (Session)event.getValue();
		
		if(validator.isValidate(session)) {
			repositories.addRepository(session);
		} else {
			repositories.removeRepository(session);
		}
		
	}
}