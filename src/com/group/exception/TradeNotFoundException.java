package com.group.exception;

public class TradeNotFoundException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public TradeNotFoundException()
	{
		super();
	}
	public TradeNotFoundException(String message)
	{
		super(message);
	}
}
