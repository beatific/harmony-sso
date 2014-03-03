package org.beatific.harmony.sso.policy;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.beatific.harmony.sso.session.Session;

public class Policies
{
	
	List<Policy> policies = null;

    public void setPolicies(List<Policy> policies)
    {
        this.policies = policies;
    }

    public Session decide(Session session, HttpServletRequest request)
    {
        Policy policy = null;
        Iterator<Policy> itr = policies.iterator();
        do
        {
            policy = (Policy)itr.next();
            session = policy.decide(session, request);
        } while(itr.hasNext());
        return session;
    }

    public boolean enforce(Session session, HttpServletRequest request)
    {
        Policy policy = null;
        Iterator<Policy> itr = policies.iterator();
        do
        {
            policy = (Policy)itr.next();
            if(!policy.enforce(session, request))
                return false;
        } while(itr.hasNext());
        return true;
    }
}