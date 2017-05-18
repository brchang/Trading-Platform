package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.group.command.LoginCommand;
import com.group.dao.StorageFactory;
import com.group.dao.UserDAO;
import com.group.exception.BannedUserException;
import com.group.exception.IncorrectPasswordException;
import com.group.exception.UserNotFoundException;
import com.group.istorable.UserDTO;
import com.group.viewcommand.ViewUsers;

public class TestUserJPA 
{   
    static UserDAO database = StorageFactory.getUserDAO();
    LoginCommand login = new LoginCommand();
    
    //@Test
	public void testCreateAndReadUser() throws UserNotFoundException
	{	
    	UserDTO newUser = new UserDTO("Bryan", "Chang", "bryan.chang", "Password123", "bryan.chang@group.com");
    	database.create(newUser);
    	
    	assertTrue((database.read(newUser.getUserID()).getUserID()) == newUser.getUserID());
   	}
	
    //@Test (expected=Exception.class)
	public void testDeleteUser() throws UserNotFoundException
	{	
    	UserDTO newUser2 = new UserDTO("Tom", "Sawyer", "tom.sawyer", "Password123", "tom.sawyer@group.com");
    	database.create(newUser2);
    	
    	database.delete(newUser2.getUserID());
    	database.read(newUser2.getUserID());
   	}   
    //@Test
    public void testLogin() throws BannedUserException, IncorrectPasswordException, UserNotFoundException
    {
    	UserDTO newUser3 = new UserDTO("Phil", "Ivey", "phil.ivey", "Password123", "phil.ivey@group.com");
    	database.create(newUser3);
    	
    	assertTrue(login.checkLoginDetails("phil.ivey", "Password123").getUsername().equals(newUser3.getUsername()));
    }
    //@Test (expected=IncorrectPasswordException.class)
    public void testIncorrectPassword() throws BannedUserException, IncorrectPasswordException, UserNotFoundException
    {
    	UserDTO newUser4 = new UserDTO("Bryan", "Chang", "bryan.chang", "Password123", "bryan.chang@group.com");
    	database.create(newUser4);
    	
    	login.checkLoginDetails("bryan.chang", "WrongPassword");
    }
    @Test (expected=BannedUserException.class)
    public void testUserBan() throws BannedUserException, IncorrectPasswordException, UserNotFoundException
    {
    	HashMap<Integer, Integer> portfolio = new HashMap<Integer, Integer>();
    	
    	portfolio.put(1, 50);
    	portfolio.put(2, 26);
    	portfolio.put(3, 43);
    	portfolio.put(4, 80);
    	portfolio.put(5, 10);
    	
    	HashMap<Integer, Integer> portfolio2 = new HashMap<Integer, Integer>();
    	
    	portfolio2.put(6, 1000);
    	portfolio2.put(2, 3000);
    	portfolio2.put(3, 4000);
    	portfolio2.put(4, 297);
    	portfolio2.put(5, 2039);
    	
    	//banned
    	UserDTO newUser7 = new UserDTO("Tom", "Sawyer", "tom.sawyer", "pass", "tom.sawyer@group.com");
    	newUser7.setBanned(true);
    	
    	UserDTO newUser5 = new UserDTO("Bryan", "Chang", "bryan.chang", "pass", "bryan.chang@group.com");
    	newUser5.addPermission("admin");
    	newUser5.addPermission("shareholder");
    	newUser5.setPortfolio(portfolio);
    	
    	UserDTO newUser6 = new UserDTO("John", "Smith", "john.smith", "pass", "john.smith@group.com");
    	newUser6.addPermission("broker");
    	
    	UserDTO newUser8 = new UserDTO("Phil", "Ivey", "phil.ivey", "pass", "phil.ivey@fulltilt.com");
    	newUser8.addPermission("shareholder");
    	
    	UserDTO newUser9 = new UserDTO("Edward", "Nunez", "edward.nunez", "pass", "edward.nunez@group.com");
    	newUser9.addPermission("shareholder");
    	newUser9.addPermission("admin");
    	newUser5.setPortfolio(portfolio2);
    	    	
    	database.create(newUser5);
    	database.create(newUser6);
    	database.create(newUser7);
    	database.create(newUser8);
    	database.create(newUser9);
    	
    	login.checkLoginDetails("tom.sawyer", "pass");
    	
    }
    //@Test
    public void testViewBanned()
    {
    	ViewUsers ban = new ViewUsers();
    	
    	List<UserDTO> banned = ban.viewBanned();
    	
    	for (UserDTO user : banned)
    		System.out.println(user);	
    }
    //@Test
    public void testPermissions() throws UserNotFoundException
    {
    	UserDTO user = database.read(4);
    	
    	database.update(user);
    }
}