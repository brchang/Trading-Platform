package com.group.viewcommand;

import java.util.List;

import com.group.dao.StorageFactory;
import com.group.dao.UserDAO;
import com.group.istorable.UserDTO;

public class ViewUsers 
{
	UserDAO database = StorageFactory.getUserDAO();
	
	public List<UserDTO> viewBanned()
	{
		List<UserDTO> bannedUsers = database.viewBanned();
		database.close();
		
		return bannedUsers;
	}
	public List<UserDTO> viewAllUsers()
	{
		List<UserDTO> Users = database.viewAllUsers();
		database.close();
		
		return Users;
	}
}