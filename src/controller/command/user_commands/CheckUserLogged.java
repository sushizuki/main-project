/** 
*    CheckUserLogged.java to define CheckUserLogged 
*    {purpose} 
*/ 

package controller.command.user_commands;

import javax.servlet.http.HttpSession;

import controller.command.Command;
import domain.User;

public class CheckUserLogged implements Command {
	
	private User user;
	private HttpSession session;
	private String pageToRedirect;

	public CheckUserLogged() {
		this.pageToRedirect = "shopping-cart.jsp"; //Default page to redirect
	}
	
	public void setSession(HttpSession s){
		this.session = s;
	}
	
	public HttpSession getSession(){
		return this.session;
	}
	
	public void setPageToRedirect(String pageToRedirect) {
		this.pageToRedirect = pageToRedirect;
	}
	
	public User getUser(){
		return this.user;
	}

	@Override
	public void execute() throws Exception {
		this.user = (User) this.session.getAttribute("user");
		if(this.user == null){
			this.pageToRedirect = "login.jsp";
		}

	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
