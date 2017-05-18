package com.group.exception;

public class UserFieldsMatchException extends Exception
{
	private static final long serialVersionUID = 1L;

	public UserFieldsMatchException()
	{
		super();
	}
	public UserFieldsMatchException(String message)
	{
		super(message);
	}
}
