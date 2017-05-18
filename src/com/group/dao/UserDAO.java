package com.group.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.group.exception.UserNotFoundException;
import com.group.istorable.UserDTO;

public class UserDAO implements IStorage<UserDTO>
{	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TradingPlatform"); 
    EntityManager em = emf.createEntityManager();
        
	public void create(UserDTO user)
	{				
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	public UserDTO read(int id) throws UserNotFoundException
	{   
	    UserDTO u = em.find(UserDTO.class, id);
	    
	    if (u != null)
	    	return u;
	    
	    throw new UserNotFoundException("No user with the id of \""+id+"\" found");
	}
	@SuppressWarnings("unchecked")
	public List<UserDTO> read(String username)
	{
		Query query = em.createNativeQuery("SELECT * FROM USERS WHERE USERNAME = ?1", UserDTO.class);
		query.setParameter(1, username);
		
		return query.getResultList();
	}
	public void update(UserDTO user) 
	{
	    em.getTransaction().begin();
	    
		UserDTO u = em.find(UserDTO.class, user.getUserID());
		
		if (u != null)
		{
			u.setFirstName(u.getFirstName());
			u.setLastName(u.getLastName());
			u.setPassword(u.getPassword());
			u.setUsername(u.getUsername());
			u.setEmail(u.getEmail());
			u.setBanned(u.isBanned());
			u.setPermission(u.getPermission());
		}	
		
		em.getTransaction().commit();
	}
	public void delete(int id) throws UserNotFoundException
	{
		UserDTO user = read(id); 
		
		em.getTransaction().begin();
		
		if (user != null)
			em.remove(user);
		
		em.getTransaction().commit();
	}
	public void ban(UserDTO user, boolean set) 
	{
		user.setBanned(set);
		update(user);
	}
	public void close() 
	{	
		em.close(); 
        emf.close();
	}
	@SuppressWarnings("unchecked")
	public List<UserDTO> viewBanned() 
	{
		Query query = em.createNativeQuery("SELECT * FROM USERS WHERE BANNED = ?1", UserDTO.class);
		query.setParameter(1, 1);
		
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<UserDTO> viewAllUsers() {
		Query query = em.createNativeQuery("SELECT * FROM USERS ORDER BY USERID", UserDTO.class);
		
		return query.getResultList();
	}
}