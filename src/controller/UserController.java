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

public class UserController extends HttpServlet {

private static final long serialVersionUID = 1L;
	
	//Initialises the command factory
	private static CommandFactory commandFactory = CommandFactory.init();
	
	//Servlet
	public UserController() {
		super();
        }

	//GET requests: user logout
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession(true);
		
		try {
			//Get command from request
			String action = request.getParameter("action");
			
			Command command = commandFactory.getCommand(action);
			
			//Default command: Redirect to login page
			if(command == null){ //Invalid command passed, go to default command
				view = request.getRequestDispatcher("login.jsp");
				view.forward(request, response);
				return;
        		} else {
        				
				if(command instanceof DoLogout){
					((DoLogout) command).setSession(session);
					command.execute();
				}
        		}

			//Set redirect page according to the command
			view = request.getRequestDispatcher(command.getPageToRedirect());			
			
	        } catch(Exception e){
			System.err.println("ERROR while processing request: ");
			e.printStackTrace();
			request.setAttribute("message", "failure");    
			view = request.getRequestDispatcher("404.jsp");
	        }        
        	
        	//Redirects to page 
		view.forward(request, response);  
	}
	
	//POST requests: creating, updating a user or do login
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
				
				if(!request.getParameter("redir").isEmpty() && request.getParameter("redir")!=null){
					System.out.println("Redirecionando para:"+request.getParameter("redir"));
					((DoLogin) command).setPageToRedirect("/"+request.getParameter("redir"));
					request.setAttribute("order", session.getAttribute("order"));
				} else {
					System.out.println("Não identificou redirecionamento");		
					System.out.println("Indo para "+command.getPageToRedirect());
					System.out.println("Devia ir para "+request.getParameter("redir"));
				}
			}
			System.out.println("Indo para "+command.getPageToRedirect());
			
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
