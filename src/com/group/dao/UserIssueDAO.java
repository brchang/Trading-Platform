package com.group.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.group.istorable.IssueDTO;

public class UserIssueDAO implements IStorage<IssueDTO> 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TradingPlatform"); 
    EntityManager em = emf.createEntityManager();

	public void create(IssueDTO issue) 
	{
		em.getTransaction().begin();
		em.persist(issue);
		em.getTransaction().commit();
	}
	public IssueDTO read(int id)
	{
		IssueDTO issue = em.find(IssueDTO.class, id);
		
		if (issue != null)
			return issue;
		
		return null;
	}
	public void update(IssueDTO issue) 
	{
		em.getTransaction().begin();
		
		IssueDTO i = em.find(IssueDTO.class, issue.getIssueID());
		
		if (i != null)
		{
			i.setIssue(i.getIssue());
			i.setMessage(i.getMessage());
			i.setStatus(i.getStatus());
		}
		
	}
	public void delete(int id) 
	{
		IssueDTO issue = read(id);
		
		em.getTransaction().begin();
		
		if (issue != null)
			em.remove(issue);
		
		em.getTransaction().commit();
	}
	
	public void close() 
	{	
		em.close(); 
        emf.close();
	}

}
