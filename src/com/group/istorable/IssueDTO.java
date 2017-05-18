package com.group.istorable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="ISSUES")
public class IssueDTO 
{
	@Id
	@SequenceGenerator(name="issue_seq", sequenceName="ISSUE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="issue_seq")
	private int issueID;
	private int userID;
	private String message;
	private String status;
	private String issue;
	
	public IssueDTO(){}
	
	public IssueDTO(int userID, String message, String issue)
	{
		this.userID = userID;
		this.message = message;
		this.status = "Outstanding";
		this.issue = issue;
	}

	public int getIssueID() {
		return userID;
	}
	public void setIssueID(int issueID) {
		this.issueID = issueID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
}
