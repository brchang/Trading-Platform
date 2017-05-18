package com.group.viewcommand;

import java.util.List;

import com.group.dao.StorageFactory;
import com.group.dao.TradeReqDAO;
import com.group.istorable.TradeReqDTO;

public class ViewTrades 
{	
	public List<TradeReqDTO> available()
	{
		TradeReqDAO database = StorageFactory.getTradeReqDAO();
		List<TradeReqDTO> available = database.viewAvailable();
		database.close();
		
		return available;
	}
	public List<TradeReqDTO> allRequests()
	{
		TradeReqDAO database = StorageFactory.getTradeReqDAO();
		List<TradeReqDTO> available = database.viewAll();
		database.close();
		
		return available;
	}
}