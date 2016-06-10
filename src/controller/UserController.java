/** 
 *    UserController.java to define UserController 
 *    Manages POST and GET requests about User related operations
 *    and shows exceptions handling.
 */ 

package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.Command;
import controller.command.CommandFactory;
import controller.command.user_commands.DoLogin;
import controller.command.user_commands.DoLogout;

/**
 * Servlet UserController
 */
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Command factory uses Factory and Command design patterns
	 */
	private static CommandFactory commandFactory = CommandFactory.init();

	//Servlet
	public UserController() {
		super();
	}
	
	//Returns proper command according to request to controller
	private Command getCommand(HttpServletRequest request){
		
		assert request != null: "Invalid Request to user controller";
		
		//Get command from request
		String action = request.getParameter("action");

		Command command = commandFactory.getCommand(action);
		
		return command;
	}
	
	//Configure pre-conditions of a command before it is executed
	private void prepareCommand(HttpServletRequest request, Command command){
		
		HttpSession session = request.getSession(true);
		
		if(command instanceof DoLogout){
			((DoLogout) command).setSession(session);
		} else {
			if(command instanceof DoLogin){	
				((DoLogin) command).setEmail(request.getParameter("email"));
				((DoLogin) command).setPassword(request.getParameter("password"));				
			}else{
				// Do nothing
			}
		}
	}
	
	//Set pos-conditions of a command after it is executed
	private void setPosCommandAttributes(HttpServletRequest request, Command command){
		
		HttpSession session = request.getSession(true);
		
		if(command instanceof DoLogin){	
			session.setAttribute("user", ((DoLogin) command).getUser());
			request.setAttribute("user", ((DoLogin) command).getUser());
			request.setAttribute("order", session.getAttribute("order"));

			if(!request.getParameter("redir").isEmpty() && request.getParameter("redir")!=null){
				((DoLogin) command).setPageToRedirect(request.getParameter("redir"));
			}else{
				// Do nothing
			}
		}else{
			// Do nothing
		}
	}

	/**
	 * Handles and redirects GET requests: do logout.
	 * @param request contains request information for this servlet
	 * @param response sends response information from this servlet to the client
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		RequestDispatcher view = null;

		try {
			
			Command command = getCommand(request);				

			//Invalid command, go to login page
			if(command == null){ 
				view = request.getRequestDispatcher("login.jsp");
			} else {
				prepareCommand(request, command);
				command.execute();
				
				//Set redirect page according to the command
				view = request.getRequestDispatcher(command.getPageToRedirect());
			}			

			//Redirects to page 
			view.forward(request, response);  

		} catch(Exception exception){
			System.err.println("ERROR while processing request in user controller: ");
			exception.printStackTrace();
			view = request.getRequestDispatcher("404.jsp");
		}        
	}

	/**
	 * Handle POST requests: creating, updating a user or do login.
	 * @param request contains request information for this servlet
	 * @param response sends response information from this servlet to the client
	 * @throws ServletException 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		Command command = getCommand(request);

		try {

			if(command instanceof DoLogin){	
				prepareCommand(request, command);
				command.execute();
				setPosCommandAttributes(request, command);
			}	
			//Redirects to page
			response.sendRedirect(request.getContextPath()+'/'+command.getPageToRedirect());
			
		}catch (Exception exception) {
			String message = "ERROR while processing request for User: "+exception.getMessage();
			request.setAttribute("error", message);
			throw new ServletException(message);
		}
	}
}
