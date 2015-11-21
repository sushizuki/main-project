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
	
	private static CommandFactory cf = CommandFactory.init();
       
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession(true);
		
		try {
        	
        	//Get command from request
        	String action = request.getParameter("action");
        				
        	Command command = cf.getCommand(action);
        	
        	//Default command: Redirect to login page
        	if(command == null){//Invalid command passed, go to default command
        		view = request.getRequestDispatcher("login.jsp");
        		view.forward(request, response);
        		return;
        	}
        				
			if(command instanceof DoLogout){
				((DoLogout) command).setSession(session);
				command.execute();
			}

			//Set redirection
			view = request.getRequestDispatcher(command.getPageToRedirect());			
			
        }catch(Exception e){
			System.err.println("ERROR while processing request: ");
			e.printStackTrace();
			request.setAttribute("message", "failure");    
			view = request.getRequestDispatcher("index.jsp");
        }        
        
		view.forward(request, response);  
	}
	
	//POST for creating, updating a user or do login
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view = null;
		HttpSession session = request.getSession(true);
		
		try {

			String action = request.getParameter("action");
			
			Command command = cf.getCommand(action);
						
			if(command instanceof DoLogin){	
				//if user not logged
				if(session.getAttribute("user") == null){
					((DoLogin) command).setEmail(request.getParameter("email"));
					((DoLogin) command).setPassword(request.getParameter("password"));
					command.execute();
					session.setAttribute("user", ((DoLogin) command).getUser());
					request.setAttribute("user", ((DoLogin) command).getUser());
					
					if(!request.getParameter("redir").isEmpty()){
						System.out.println("redir "+request.getParameter("redir"));
						((DoLogin) command).setPageToRedirect((request.getParameter("redir")));
					}
				}
			}
			
			view = request.getRequestDispatcher(command.getPageToRedirect());
			
		}catch (Exception e) {
			System.err.println("ERROR while processing request for User: ");
			e.printStackTrace();
			request.setAttribute("message", "failure");   
			view = request.getRequestDispatcher("/404.jsp");
        }
		
		//Redirect
        view.forward(request, response);
		//response.sendRedirect("menu");
		
	}
}
