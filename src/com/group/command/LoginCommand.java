package com.group.command;

import java.util.List;

import com.group.dao.StorageFactory;
import com.group.dao.UserDAO;
import com.group.exception.BannedUserException;
import com.group.exception.IncorrectPasswordException;
import com.group.exception.UserNotFoundException;
import com.group.istorable.UserDTO;

public class LoginCommand 
{
	UserDAO database = StorageFactory.getUserDAO();
	UserDTO user;
	
	public UserDTO checkLoginDetails(String username, String password) throws BannedUserException, IncorrectPasswordException, UserNotFoundException
	{		
		List<UserDTO> dbUser = database.read(username);
		
		for (UserDTO user : dbUser)
			this.user = user;
		
		if (user == null)
		{
			database.close();
			throw new UserNotFoundException("There is no user \""+username+"\"");
		}
		if (user.isBanned())
		{
			database.close();
			throw new BannedUserException(user.getUsername()+" is banned");			
		}
		if ((user.getPassword()).equals(password))
		{
			user.setLoginAttempts(0);
			database.update(user);
			database.close();
			return user;	
		}
		else
		{
			String message = "";
			
			user.setLoginAttempts(user.getLoginAttempts() + 1);
			database.update(user);
			
			if (user.getLoginAttempts() == 3)
			{
				database.ban(user, true);
				message += " too many times. You have been banned";
			}
			
			database.close();
			throw new IncorrectPasswordException("You have entered an incorrect password"+message);				
		}				
	}
}
