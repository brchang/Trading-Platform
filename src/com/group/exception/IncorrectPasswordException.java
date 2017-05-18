package com.group.exception;

public class IncorrectPasswordException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public IncorrectPasswordException()
	{
		super();
	}	
	public IncorrectPasswordException(String message)
	{
		super(message);
	}
}
