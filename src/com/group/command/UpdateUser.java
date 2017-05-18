package com.group.command;

import java.util.ArrayList;

import com.group.dao.UserDAO;
import com.group.exception.UserNotFoundException;
import com.group.istorable.UserDTO;

public class UpdateUser 
{
	UserDAO db = new UserDAO();
	ArrayList<String> perm = new ArrayList<String>();
	
	public UpdateUser(int id, UserDTO user, String admin, String broker, String shareholder, String ban) throws UserNotFoundException
	{
		if (admin != null)
			perm.add(admin);
		if (broker !=null)
			perm.add(broker);
		if (shareholder != null)
			perm.add(shareholder);
		
		UserDTO userUpdate = db.read(id);
		
		if (ban.equals("yes")) {
			userUpdate.setBanned(true);
		} else {
			userUpdate.setBanned(false);
		}
		
		userUpdate.setFirstName(user.getFirstName());
		userUpdate.setLastName(user.getLastName());
		userUpdate.setEmail(user.getEmail());
		userUpdate.setPassword(user.getPassword());
		userUpdate.setUsername(user.getUsername());
		userUpdate.setPermission(perm);

		db.update(userUpdate);
		db.close();
	}
}
