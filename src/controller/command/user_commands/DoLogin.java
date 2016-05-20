/** 
*    DoLogin.java to define DoLogin 
*    {purpose} 
*/ 

package controller.command.user_commands;

import controller.command.Command;
import dao.UserDAO;
import domain.Administrator;
import domain.Client;
import domain.User;

public class DoLogin implements Command {
	
	private UserDAO userDao;
	private User user;
	private String email;
	private String password;
	private String pageToRedirect;

	public DoLogin() {
		this.user = null;
		this.userDao = new UserDAO();
		this.pageToRedirect = "menu"; //default page to redirect
	}
	
	public User getUser(){
		return this.user;
	}	
	
	public void setPageToRedirect(String p){
		this.pageToRedirect = p;
	}
	
	public void setEmail(String e){
		this.email = e;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void execute() throws Exception {
		this.user = userDao.login(this.email, this.password);
		
		if(this.user instanceof Administrator){
			this.setPageToRedirect("adm/Order?action=getOrderList");
		} else if(this.user == null) { //Fail to log in
			this.setPageToRedirect("login.jsp?err=1");
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
