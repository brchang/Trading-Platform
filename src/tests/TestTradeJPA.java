package tests;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Test;

import com.group.dao.StockDAO;
import com.group.dao.TradeReqDAO;
import com.group.istorable.StockDTO;
import com.group.istorable.TradeReqDTO;

public class TestTradeJPA 
{
    static TradeReqDAO tradeDatabase = new TradeReqDAO();
    static StockDAO stockDatabase = new StockDAO();
   
    @AfterClass
    public static void close()
    {
    	tradeDatabase.close();
    	stockDatabase.close();
    }
    
    @Test
    public void createTrade()
    {
    	long time = new Date().getTime();
    	
    	TradeReqDTO trade1 = new TradeReqDTO(1, 20, 1, null, time);
    	TradeReqDTO trade2 = new TradeReqDTO(2, 40, 2, null, time);
    	TradeReqDTO trade3 = new TradeReqDTO(3, 80, null, 4, time);
    	TradeReqDTO trade4 = new TradeReqDTO(4, 10, null, 1, time);
    	TradeReqDTO trade5 = new TradeReqDTO(5, 100, null, 5, time);
    	TradeReqDTO trade6 = new TradeReqDTO(6, 63, 4, null, time);
    	TradeReqDTO trade7 = new TradeReqDTO(2, 40, 2, null, time);
    	TradeReqDTO trade8 = new TradeReqDTO(3, 29, 3, null, time);
    	TradeReqDTO trade9 = new TradeReqDTO(4, 38, 6, null, time);
    	TradeReqDTO trade10 = new TradeReqDTO(1, 40, 2, null, time);
    	
    	tradeDatabase.create(trade1);
    	tradeDatabase.create(trade2);
    	tradeDatabase.create(trade3);
    	tradeDatabase.create(trade4);
    	tradeDatabase.create(trade5);
    	tradeDatabase.create(trade6);
    	tradeDatabase.create(trade7);
    	tradeDatabase.create(trade8);
    	tradeDatabase.create(trade9);
    	tradeDatabase.create(trade10); 
    	
    	StockDTO stock1 = new StockDTO(1,"Apple","AAPL",593);
    	StockDTO stock2 = new StockDTO(2,"Google","GOOG",533);
    	StockDTO stock3 = new StockDTO(3,"IBM","IBM",192);
    	StockDTO stock4 = new StockDTO(4,"Microsoft","MSFT",40);
    	StockDTO stock5 = new StockDTO(5,"Samsung","SSNLF",1384);
    	StockDTO stock6 = new StockDTO(6,"CBS","CBS",350);

    	stockDatabase.create(stock1);
    	stockDatabase.create(stock2);
    	stockDatabase.create(stock3);
    	stockDatabase.create(stock4);
    	stockDatabase.create(stock5);
    	stockDatabase.create(stock6);
    }
}
