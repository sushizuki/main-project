/** 
*    DoLogout.java to define DoLogout 
*    Performs user logout invalidating session attributes
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
	
	public void setSession(HttpSession session){
		this.session = session;
	}

	@Override
	public void execute() throws Exception {
		session.removeAttribute("user");
		session.removeAttribute("order");
		session.invalidate();
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
