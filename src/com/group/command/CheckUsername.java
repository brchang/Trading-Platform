package com.group.command;

import com.group.dao.StorageFactory;
import com.group.dao.UserDAO;
import com.group.exception.UserNotFoundException;

public class CheckUsername 
{
	UserDAO database = StorageFactory.getUserDAO();
	
	public boolean checkUsername(String username) throws UserNotFoundException
	{		
		int check = database.read(username).size();
		
		database.close();
		
		if (check == 0)
		{
			return true;
		}		
			return false;
	}
}
