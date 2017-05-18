package com.group.command;

import com.group.dao.StorageFactory;
import com.group.dao.UserDAO;
import com.group.exception.UserNotFoundException;
import com.group.istorable.UserDTO;

public class BanCommand 
{
	UserDAO database = StorageFactory.getUserDAO();
	UserDTO user;
	
	public void ban(int userID, boolean set) throws UserNotFoundException
	{
		UserDTO user = database.read(userID);
		user.setBanned(set);
		database.update(user);
		
		database.close();
	}
}
