package com.group.dao;

public class StorageFactory 
{
    public static UserDAO getUserDAO()
    {
    	return new UserDAO();
    }
    public static TradeReqDAO getTradeReqDAO()
    {
    	return new TradeReqDAO();
    }
    public static UserIssueDAO getUserIssueDAO()
    {
    	return new UserIssueDAO();
    }
    public static StockDAO getStockDAO()
    {
    	return new StockDAO();
    }
}