package com.group.exception;

public class BannedUserException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public BannedUserException()
	{
		super();
	}
	public BannedUserException(String message)
	{
		super(message);
	}
}
