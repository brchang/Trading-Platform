package com.group.command;

import java.util.Date;
import java.util.HashMap;

import com.group.dao.StorageFactory;
import com.group.dao.TradeReqDAO;
import com.group.dao.UserDAO;
import com.group.exception.NotEnoughSharesException;
import com.group.exception.UserNotFoundException;
import com.group.istorable.TradeReqDTO;
import com.group.istorable.UserDTO;

public class SellRequestCommand 
{
	UserDAO userDB = StorageFactory.getUserDAO();
	TradeReqDAO tradeDB = StorageFactory.getTradeReqDAO();
	long time = new Date().getTime();
	
	public SellRequestCommand(int shares, int stockID, int userID) throws UserNotFoundException, NotEnoughSharesException
	{
		UserDTO user = userDB.read(userID);
		HashMap<Integer, Integer> portfolio = user.getPortfolio();
		
		int portShares = portfolio.get(stockID);
		
		System.out.println(portShares);
		int updatedShares = portShares - shares;
		
		if(updatedShares < 0)
		{
			throw new NotEnoughSharesException();
		}
		if (updatedShares == 0)
		{
			portfolio.remove(stockID);
		}
		
		portfolio.put(stockID, updatedShares);
		
		user.setPortfolio(portfolio);
		userDB.update(user);
		userDB.close();
		
		TradeReqDTO tradeReq = new TradeReqDTO(stockID, shares, user.getUserID(), null, time);
		
		tradeDB.create(tradeReq);
		tradeDB.close();
	}
}
