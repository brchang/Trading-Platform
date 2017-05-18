package com.group.istorable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="STOCKS")
public class StockDTO
{
	@Id
	private int stockID;
	private String company_name;
	private String symbol;
	private int price;
	
	public StockDTO(int stockID, String company, String symbol, int price)
	{
		this.stockID = stockID;
		this.company_name = company;
		this.symbol = symbol;
		this.price = price;
	}
	
	public StockDTO(){}
	
	public int getStockID() {
		return stockID;
	}
	public void setStockID(int stockID) {
		this.stockID = stockID;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
