package com.group.istorable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TRADE_REQUESTS")
public class TradeReqDTO 
{
	@Id
	@SequenceGenerator(name="trade_seq", sequenceName="TRADE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="trade_seq")
	private int requestID;
	private int stockID;
	private int shares;
	private Integer sellerID = null;
	private Integer buyerID = null;
	private long timestamp;
	
	public TradeReqDTO(int stockID, int shares, Integer sellerID, Integer buyerID, long timestamp)
	{
		this.stockID = stockID;
		this.shares = shares;
		this.sellerID = sellerID;
		this.buyerID = buyerID;
		this.timestamp = timestamp;
	}
	
	public TradeReqDTO(){}
	
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public int getStockID() {
		return stockID;
	}
	public void setStockID(int stockID) {
		this.stockID = stockID;
	}
	public int getShares() {
		return shares;
	}
	public void setShares(int shares) {
		this.shares = shares;
	}
	public Integer getSellerID() {
		return sellerID;
	}
	public Integer getBuyerID() {
		return buyerID;
	}
	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}
	public void setBuyerID(int buyerID) {
		this.buyerID = buyerID;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
