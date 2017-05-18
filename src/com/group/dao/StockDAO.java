package com.group.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.group.exception.TradeNotFoundException;
import com.group.exception.UserNotFoundException;
import com.group.istorable.StockDTO;

public class StockDAO implements IStorage<StockDTO>
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TradingPlatform"); 
    EntityManager em = emf.createEntityManager();

	public void create(StockDTO stock) 
	{
		em.getTransaction().begin();
		em.persist(stock);
		em.getTransaction().commit();
	}
	public StockDTO read(int id)
	{
		 StockDTO s = em.find(StockDTO.class, id);
		    
		 return s;
	}
	@SuppressWarnings("unchecked")
	public List<StockDTO> getStockInfo()
	{
		Query query = em.createNativeQuery("SELECT * FROM STOCKS", StockDTO.class);
		
		return query.getResultList();
	}
	public void close() 
	{	
		em.close(); 
        emf.close();
	}	
	public void update(StockDTO u) {}
	public void delete(int id) throws UserNotFoundException, TradeNotFoundException {}
}