package com.group.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.group.exception.TradeNotFoundException;
import com.group.istorable.TradeReqDTO;

public class TradeReqDAO implements IStorage<TradeReqDTO>
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TradingPlatform"); 
    EntityManager em = emf.createEntityManager();

	public void create(TradeReqDTO tradeReq) 
	{
		em.getTransaction().begin();
		em.persist(tradeReq);
		em.getTransaction().commit();
	}
	public TradeReqDTO read(int id) throws TradeNotFoundException 
	{
		TradeReqDTO t = em.find(TradeReqDTO.class, id);
		
		if (t != null)
			return t;
		
		throw new TradeNotFoundException();
	}
	public void update(TradeReqDTO tradeReq)
	{
		em.getTransaction().begin();
		
		TradeReqDTO t = em.find(TradeReqDTO.class, tradeReq.getRequestID());
		
		if (t != null)
		{
			t.setBuyerID(t.getBuyerID());
			t.setSellerID(t.getSellerID());
			t.setShares(t.getShares());
			t.setStockID(t.getStockID());
			t.setTimestamp(t.getTimestamp());
		}
	}
	public void delete(int id) throws TradeNotFoundException 
	{
		TradeReqDTO trade = read(id);
		
		em.getTransaction().begin();
		
		if (trade != null)
			em.remove(trade);
		
		em.getTransaction().commit();	
	}
	@SuppressWarnings("unchecked")
	public List<TradeReqDTO> viewAvailable()
	{
		Query query = em.createNativeQuery("SELECT * FROM TRADE_REQUESTS WHERE BUYERID IS NULL ORDER BY REQUESTID", TradeReqDTO.class);
		
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<TradeReqDTO> viewAll()
	{
		Query query = em.createNativeQuery("SELECT * FROM TRADE_REQUESTS WHERE BUYERID IS NULL OR SELLERID IS NULL ORDER BY REQUESTID", TradeReqDTO.class);
		
		return query.getResultList();
	}
	public void close() 
	{	
		em.close(); 
        emf.close();
	}
}