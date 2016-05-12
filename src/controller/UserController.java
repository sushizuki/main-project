/** 
 *    UserController.java to define UserController 
 *    Manages POST and GET requests about User related operations
 *    and shows exceptions handling.
 */ 

package controller;

import java.io.IOException;

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
	
	private Command getCommand(HttpServletRequest request){
		//Get command from request
		String action = request.getParameter("action");

		Command command = commandFactory.getCommand(action);
		
		return command;
	}
	
	private void prepareCommand(HttpServletRequest request, Command command){
		
		HttpSession session = request.getSession(true);
		
		if(command instanceof DoLogout){
			((DoLogout) command).setSession(session);
		} else {
			if(command instanceof DoLogin){	
				
			}
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

		} catch(Exception e){
			System.err.println("ERROR while processing request: ");
			e.printStackTrace();
			request.setAttribute("message", "failure");    
			view = request.getRequestDispatcher("404.jsp");
		}        
	}

	/**
	 * Handle POST requests: creating, updating a user or do login.
	 * @param request contains request information for this servlet
	 * @param response sends response information from this servlet to the client
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view = null;
		HttpSession session = request.getSession(true);

		try {
			String action = request.getParameter("action");

			//Default action: try to do login
			if(action == null || action.isEmpty()){
				action = "doLogin";
			} else {
				//Do nothing
			}

			Command command = commandFactory.getCommand(action);

			if(command instanceof DoLogin){	
				((DoLogin) command).setEmail(request.getParameter("email"));
				((DoLogin) command).setPassword(request.getParameter("password"));
				command.execute();
				session.setAttribute("user", ((DoLogin) command).getUser());
				request.setAttribute("user", ((DoLogin) command).getUser());

				if(command.getPageToRedirect().contains("adm/")){
					response.sendRedirect("adm/Order?action=getOrderList");
					return;
				}

				if(request.getParameter("redir").isEmpty() || request.getParameter("redir")==null){
					((DoLogin) command).setPageToRedirect("menu");	
					System.out.println(request.getParameter("redir"));				
				} else {
					((DoLogin) command).setPageToRedirect("/"+request.getParameter("redir"));
					request.setAttribute("order", session.getAttribute("order"));
					System.out.println(request.getParameter("redir"));
				}
			}

			view = request.getRequestDispatcher(command.getPageToRedirect());

		}catch (Exception e) {
			System.err.println("ERROR while processing request for User: ");
			e.printStackTrace();
			request.setAttribute("message", "failure");   
			view = request.getRequestDispatcher("/404.jsp");
		}

		//Redirects to page
		view.forward(request, response);

	}
}
