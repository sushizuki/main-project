/** 
*    DoLogout.java to define DoLogout 
*    {purpose} 
*/ 

package controller.command.user_commands;

import javax.servlet.http.HttpSession;

import controller.command.Command;

public class DoLogout implements Command {
	
	String pageToRedirect;
	HttpSession session;

	public DoLogout() {
		this.pageToRedirect = "index.jsp";
	}
	
	public void setSession(HttpSession s){
		this.session = s;
	}

	@Override
	public void execute() throws Exception {
		session.removeAttribute("user");
		session.removeAttribute("order");
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
