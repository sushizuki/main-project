/** 
*    DoLogout.java to define DoLogout 
*    {purpose} 
*/ 

package controller.command.user_commands;

import javax.servlet.http.HttpSession;

import controller.command.Command;
import domain.Administrator;

public class DoLogout implements Command {
	
	String pageToRedirect;
	HttpSession session;

	public DoLogout() {
		this.pageToRedirect = "index.jsp";
	}
	
	public void setSession(HttpSession session){
		this.session = session;
	}

	@Override
	public void execute() throws Exception {
		if(session.getAttribute("user") instanceof Administrator){
			this.pageToRedirect = "../index.jsp";
			System.out.println("ERA ADM");
		}
		session.removeAttribute("user");
		session.removeAttribute("order");
		session.invalidate();
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
