package com.group.istorable;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinColumn;

@Entity(name="USERS")
public class UserDTO implements IStorable
{	
	@Id
	@SequenceGenerator(name="user_seq", sequenceName="USER_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
	private int userID;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private boolean banned;
	private int loginAttempts;
	@JoinTable(name="EMP_PROJ",
		       joinColumns={@JoinColumn(name="USER_PERMISSION", referencedColumnName="ID")},
		       inverseJoinColumns={@JoinColumn(name="PROJ_ID", referencedColumnName="ID")})
	private ArrayList<String> permission = new ArrayList<String>();
	@JoinTable(name="EMP_PROJ",
		       joinColumns={@JoinColumn(name="USER_PORTFOLIO", referencedColumnName="ID")},
		       inverseJoinColumns={@JoinColumn(name="PROJ_ID", referencedColumnName="ID")})
	private HashMap<Integer, Integer> portfolio;
	
	public UserDTO(String firstName, String lastName, String username, String password, String email) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public UserDTO(){}
	
	//Getters & Setters
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isBanned() {
		return banned;
	}
	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	public int getLoginAttempts() {
		return loginAttempts;
	}
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}
	public ArrayList<String> getPermission() {
		return permission;
	}
	public void setPermission(ArrayList<String> permission) {
		this.permission = permission;
	}
	public void addPermission(String permission)
	{
		this.permission.add(permission);
	}
	public HashMap<Integer, Integer> getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(HashMap<Integer, Integer> portfolio) {
		this.portfolio = portfolio;
	}

}