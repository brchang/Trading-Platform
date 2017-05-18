package com.group.viewcommand;

import java.util.List;

import com.group.dao.StockDAO;
import com.group.dao.StorageFactory;
import com.group.istorable.StockDTO;

public class ViewStocks 
{
	StockDAO database = StorageFactory.getStockDAO();
	
	public List<StockDTO> Stocks()
	{
		List<StockDTO> stocks = database.getStockInfo();
		database.close();
		
		return stocks;
	}
}