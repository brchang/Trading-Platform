package com.group.exception;

public class NotEnoughSharesException extends Exception
{
	private static final long serialVersionUID = 1L;

	public NotEnoughSharesException()
	{
		super();
	}
	public NotEnoughSharesException(String message)
	{
		super(message);
	}
}