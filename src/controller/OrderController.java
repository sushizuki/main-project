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
import controller.command.order_commands.AddAdditionalsToOrder;
import controller.command.order_commands.GetAvailableAdditionals;
import controller.command.order_commands.GetOrder;
import controller.command.order_commands.NewOrder;
import controller.command.order_commands.SetClientToOrder;
import controller.command.order_commands.SetCollectTime;
import controller.command.user_commands.CheckUserLogged;
import domain.Client;
import domain.Order;


public class OrderController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static CommandFactory cf = CommandFactory.init();
       
    public OrderController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession(true);
		
		try {
			
			String action = request.getParameter("action");
			
			//Default action: List Products
        	if(action == null || action.isEmpty()){
        		action = "getOrder";
        		System.out.println("ACTION GET ORDER DEFAULT");
        	}
			
			Command command = cf.getCommand(action);
			System.out.println("EXECUTING GET COMMAND: "+action);
			
			if(command instanceof GetOrder){	
				System.out.println("GETTING ORDER");
				((GetOrder) command).setSession(session);				
				command.execute();		

				Command command2 = cf.getCommand("getAvailableAdditionals");
				command2.execute();
				request.setAttribute("additionals", ((GetAvailableAdditionals)command2).getAdditionals());
			}

			view = request.getRequestDispatcher(command.getPageToRedirect());
			
        } catch (Exception e) {
			System.err.println("ERROR while recovering order: ");
			e.printStackTrace();
			request.setAttribute("message", "failure");   
			view = request.getRequestDispatcher("/404.jsp");
        }
		
		//Redirect
        view.forward(request, response);	
		
	
	}

	//Every POST is a new Order
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession(true);
		
		try {
			
			String action = request.getParameter("action");
			
			//Default action
			if(action == null || action.isEmpty()){
        		action = "getOrder";
        		System.out.println("ACTION GET ORDER DEFAULT");
        	}
			
			Command command = cf.getCommand(action);
			System.out.println("EXECUTING POST COMMAND: "+action);
			System.out.println("ORDER IN SESSION: "+session.getAttribute("order"));
			System.out.println("ORDER IN REQUEST: "+request.getAttribute("order"));
			
			if(command instanceof GetOrder){	
				((GetOrder) command).setSession(session);				
				command.execute();
				request.setAttribute("order", ((GetOrder) command).getOrder());
				session.setAttribute("order", ((GetOrder)command).getOrder());

				Command command2 = cf.getCommand("getAvailableAdditionals");
				command2.execute();
				request.setAttribute("additionals", ((GetAvailableAdditionals)command2).getAdditionals());
			} else if(command instanceof NewOrder){			
				((NewOrder) command).setItems(request.getParameterValues("products[]"), request.getParameterValues("prod_quantity[]"));
				command.execute();
				request.setAttribute("order", ((NewOrder)command).getOrder());
				session.setAttribute("order", ((NewOrder)command).getOrder());
				
				command = cf.getCommand("getAvailableAdditionals");
				command.execute();
				request.setAttribute("additionals", ((GetAvailableAdditionals)command).getAdditionals());
			} if(command instanceof AddAdditionalsToOrder){
				Command command2;
				command2 = cf.getCommand("checkUserLogged");
				((CheckUserLogged)command2).setSession(session);
				
				//IF user was not logged
				if( ((CheckUserLogged)command2).getUser() != null ){
					view = request.getRequestDispatcher(command2.getPageToRedirect());
			        view.forward(request, response);
			        return;
				} 
				
				command2 = cf.getCommand("setClientToOrder");
				((SetClientToOrder)command2).setOrder((Order)session.getAttribute("order"));
				((SetClientToOrder)command2).setClient((Client)session.getAttribute("user"));
				command2.execute();
				session.setAttribute("order", ((SetClientToOrder)command2).getOrder());	
				request.setAttribute("order", ((SetClientToOrder)command2).getOrder());
				
				
				((AddAdditionalsToOrder) command).setOrder((Order)session.getAttribute("order"));
				((AddAdditionalsToOrder) command).setAdditionalsIds(request.getParameterValues("additional[]"));
				((AddAdditionalsToOrder) command).setReceiving(Integer.valueOf(request.getParameter("receiving")));
				command.execute();
				request.setAttribute("order", ((AddAdditionalsToOrder)command).getOrder());
				session.setAttribute("order", ((AddAdditionalsToOrder)command).getOrder());					
			} else if(command instanceof SetCollectTime){
				((SetCollectTime)command).setOrder((Order)session.getAttribute("order"));
				((SetCollectTime)command).setCollectTime(request.getParameter("date"), request.getParameter("time"));
				command.execute();
			}
						
	 		view = request.getRequestDispatcher(command.getPageToRedirect());
			
        } catch (Exception e) {
			System.err.println("ERROR while creating order: ");
			e.printStackTrace();
			request.setAttribute("message", "failure");   
			view = request.getRequestDispatcher("/404.jsp");
        }
		
		//Redirect
        view.forward(request, response);	
		
	}
}
