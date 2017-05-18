package com.group.command;

import java.util.List;

import com.group.dao.StorageFactory;
import com.group.dao.UserDAO;
import com.group.dao.UserIssueDAO;
import com.group.exception.UserFieldsMatchException;
import com.group.exception.UserNotFoundException;
import com.group.istorable.IssueDTO;
import com.group.istorable.UserDTO;

public class ResetPassCommand 
{
	UserDAO userDB = StorageFactory.getUserDAO();
	UserIssueDAO issueDB = StorageFactory.getUserIssueDAO();
	UserDTO user;
	
	public void reset(String username, String firstname, String lastname, String email, String message) 
			throws UserNotFoundException, UserFieldsMatchException
	{
		List<UserDTO> dbUser = userDB.read(username);
		
		for (UserDTO user : dbUser)
			this.user = user;
		
		if (user == null)
		{
			userDB.close();
			issueDB.close();
			throw new UserNotFoundException("There is no user \""+username+"\"");
		}
		
		if (user.getEmail().equals(email) && user.getFirstName().equals(firstname) 
				&& user.getLastName().equals(lastname) && user.getUsername().equals(username))
		{
			IssueDTO issue = new IssueDTO(user.getUserID(), message, "Reset Password");
			issueDB.create(issue);
		} else {
			issueDB.close();
			userDB.close();
			throw new UserFieldsMatchException();
		}
		
		issueDB.close();
		userDB.close();
	}
}
