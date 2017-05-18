package com.group.command;

import java.util.Date;
import java.util.List;

import com.group.dao.StorageFactory;
import com.group.dao.TradeReqDAO;
import com.group.istorable.StockDTO;
import com.group.istorable.TradeReqDTO;
import com.group.viewcommand.ViewStocks;

public class BuyRequestCommand 
{
	ViewStocks view = new ViewStocks();
	TradeReqDAO database = StorageFactory.getTradeReqDAO();
	long time = new Date().getTime();
	
	public BuyRequestCommand(String company, int shares, int buyerID)
	{
		List<StockDTO> stocks = view.Stocks();
		
		int stockID = 0;
		
		for (StockDTO stock : stocks)
		{
			if (stock.getCompany_name().equals(company))
			{
				stockID = stock.getStockID();
			}
		}
		
		TradeReqDTO trade = new TradeReqDTO(stockID, shares, null, buyerID, time);
		
		database.create(trade);
		database.close();
	}
}

