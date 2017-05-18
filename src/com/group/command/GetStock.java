package com.group.command;

import com.group.dao.StockDAO;
import com.group.dao.StorageFactory;
import com.group.istorable.StockDTO;

public class GetStock 
{
	StockDAO database = StorageFactory.getStockDAO();
	
	public StockDTO get(int stockID)
	{
		StockDTO stock = database.read(stockID);
		database.close();
		
		return stock;
	}
}
