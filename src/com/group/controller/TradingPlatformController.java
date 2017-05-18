package com.group.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group.command.BuyRequestCommand;
import com.group.command.CheckUsername;
import com.group.command.GetStock;
import com.group.command.LoginCommand;
import com.group.command.ResetPassCommand;
import com.group.command.SellRequestCommand;
import com.group.command.UpdateUser;
import com.group.dao.StorageFactory;
import com.group.dao.UserDAO;
import com.group.exception.BannedUserException;
import com.group.exception.IncorrectPasswordException;
import com.group.exception.NotEnoughSharesException;
import com.group.exception.UserFieldsMatchException;
import com.group.exception.UserNotFoundException;
import com.group.istorable.StockDTO;
import com.group.istorable.TradeReqDTO;
import com.group.istorable.UserDTO;
import com.group.viewcommand.ViewStocks;
import com.group.viewcommand.ViewTrades;
import com.group.viewcommand.ViewUsers;
 
@Controller
public class TradingPlatformController 
{
	@RequestMapping("/")
	public String homeHandler(Model model)
	{
		return "index.html";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginServlet(String username, String password, HttpServletRequest request)
	{
		LoginCommand login = new LoginCommand();
		
		try {
			UserDTO user = login.checkLoginDetails(username, password);
			request.getSession().setAttribute("user",user); 
			return "UserPage.jsp";
		} catch (BannedUserException e) {
			return "banned.jsp";
		} catch (IncorrectPasswordException e) {
			e.printStackTrace();
			return "<html><head><title>My Test Page</title></head><body>"+e+"</body></html>";
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return "<html><head><title>My Test Page</title></head><body>"+e+"</body></html>";
		}
	}
	@RequestMapping(value="/logout")
	public String logoutServlet(HttpSession session, Model model)
	{
		session.invalidate();
		
	    return "redirect:/index.html";
	}
	@RequestMapping(value="/validateUsername")
	public void validateUsernameServlet(String usernameSign, HttpServletResponse response)
	{
		CheckUsername check = new CheckUsername();

		boolean valid = false;
		
		try {
			valid = check.checkUsername(usernameSign);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    try {
			response.getWriter().write("{\"valid\":" + valid + "}");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	@RequestMapping(value="/resetPass", method=RequestMethod.POST)
	public String resetPasswordServlet(String resetUsername, String resetFirstName, String resetLastName, String resetEmail,
			String resetMessage)
	{
		ResetPassCommand pass = new ResetPassCommand();
		
		try {
			pass.reset(resetUsername, resetFirstName, resetLastName, resetEmail, resetMessage);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (UserFieldsMatchException e) {
			e.printStackTrace();
		}
		
		return "redirect:/index.html";
	}
	@RequestMapping(value="/viewUsers")
	public String viewUsersServlet(HttpServletRequest request)
	{
		ViewUsers view = new ViewUsers();
    	List<UserDTO> users = view.viewAllUsers();
    	
    	request.setAttribute("userlist",users);
		return "UserManagement.jsp";
	}
	@RequestMapping(value="/deleteUser")
	public String deleteUserServlet(String userID, HttpServletRequest request)
	{
		UserDAO database = StorageFactory.getUserDAO();
		
		try {
			database.delete(Integer.parseInt(userID));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		
		database.close();
		
		ViewUsers view = new ViewUsers();
    	List<UserDTO> users = view.viewAllUsers();
    	    	
    	request.setAttribute("userlist",users);
    	
    	return "UserManagement.jsp";
	}
	@RequestMapping(value="/editUser")
	public String editUserServlet(String userID, HttpServletRequest request)
	{
		UserDAO db = new UserDAO();
		
		String adminCheck = "";
		String brokerCheck = "";
		String shareholderCheck = "";
		
		try 
		{	
			UserDTO editUser = db.read(Integer.parseInt(userID));
			
			for (String perm :editUser.getPermission())
			{
				if (perm.equals("admin")) {
					adminCheck="checked";
				} else if (perm.equals("broker")) {
					brokerCheck="checked";
				} else if (perm.equals("shareholder")) {	
					shareholderCheck="checked";	
				}
			}
			
			request.setAttribute("adminCheck", adminCheck);
			request.setAttribute("brokerCheck", brokerCheck);
			request.setAttribute("shareholderCheck", shareholderCheck);
			
			request.setAttribute("editUser",editUser);	
		} 
		catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		
		return "EditUser.jsp";
	}
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public String updateUserServlet(String userID, String updateUsername, String updatePassword, String updateFirstName,
			String updateLastName, String updateEmail, String adminCheckbox, String shareholderCheckbox, String brokerCheckbox, 
			String banRadio, HttpServletRequest request)
	{			
		UserDTO user = new UserDTO(updateFirstName, updateLastName, updateUsername, updatePassword, updateEmail);
		
		try {
			new UpdateUser(Integer.parseInt(userID), user, adminCheckbox, shareholderCheckbox, brokerCheckbox, banRadio);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}		
		
		ViewUsers view = new ViewUsers();
    	List<UserDTO> users = view.viewAllUsers();
    	    	
    	request.setAttribute("userlist",users);
    	
		return "UserManagement.jsp";
	}
	@RequestMapping(value="/buyRequest")
	public String buyRequestServer(HttpServletRequest request)
	{
		ViewStocks view = new ViewStocks();
		List<StockDTO> stocks = view.Stocks();
		
		request.setAttribute("stocklist",stocks);
		return "BuyRequest.jsp";
	}
	@RequestMapping(value="/makeBuyRequest")
	public String makeBuyRequestServer(String numShares, String stockSelection, HttpServletRequest request)
	{
		String stockselection = request.getParameter("stockSelection").toString();
		int shares = Integer.parseInt(request.getParameter("numShares").toString());
		int userID = Integer.parseInt(request.getParameter("userID"));
		
		new BuyRequestCommand(stockselection, shares, userID);
		
	    return "UserPage.jsp";
	}
	@RequestMapping(value="/availableShares")
	public String availableSharesServlet(HttpServletRequest request)
	{
		ViewTrades view = new ViewTrades();
		ViewStocks view2 = new ViewStocks();
		
		List<TradeReqDTO> shares = view.available();
		List<StockDTO> stocks = view2.Stocks();
		
		request.setAttribute("sharelist",shares);
		request.setAttribute("stocklist",stocks);
		
		return "AvailableShares.jsp";
	}
	@RequestMapping(value="/tradeRequests")
	public String tradeRequestsServlet(HttpServletRequest request)
	{
		ViewTrades view = new ViewTrades();
		ViewStocks view2 = new ViewStocks();
		
		List<TradeReqDTO> shares = view.allRequests();
		List<StockDTO> stocks = view2.Stocks();
		
		request.setAttribute("sharelist",shares);
		request.setAttribute("stocklist",stocks);
		
		return "TradeRequests.jsp";
	}
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signUpServlet(String usernameSign, String passwordSign, String firstName, String lastName, String email)
	{
		UserDTO user = new UserDTO(firstName, lastName, usernameSign, passwordSign, email);
		UserDAO db = new UserDAO();
		db.create(user);
		
		return "index.jsp";
	}
	@RequestMapping(value="/portfolio")
	public String portfolioServlet(HttpServletRequest request)
	{
		ViewStocks view = new ViewStocks();
		List<StockDTO> stocks = view.Stocks();
		
		request.setAttribute("stocklist",stocks);
		
		return "Portfolio.jsp";
	}
	@RequestMapping(value="/sellRequest")
	public String sellRequestServlet(String viewPortStock, HttpServletRequest request)
	{
		StockDTO stock = new GetStock().get(Integer.parseInt(viewPortStock));
		request.setAttribute("stock", stock);
		
		return "SellRequest.jsp";
	}
	
	@RequestMapping(value="/makeSellRequest", method=RequestMethod.POST)
	public String makeSellRequest(String userID, String stockID, String numShares, HttpServletRequest request)
	{
		UserDAO db = StorageFactory.getUserDAO();
		
		try {
			new SellRequestCommand(Integer.parseInt(numShares), Integer.parseInt(stockID), Integer.parseInt(userID));
			UserDTO user = db.read(Integer.parseInt(userID));	
			request.getSession().setAttribute("user",user);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (NotEnoughSharesException e) {
			e.printStackTrace();
		}

		ViewStocks view = new ViewStocks();
		List<StockDTO> stocks = view.Stocks();
		
		request.setAttribute("stocklist",stocks);
		
		return "Portfolio.jsp";
	}
}