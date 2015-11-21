package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.command.Command;
import controller.command.CommandFactory;
import controller.command.order_commands.AddAdditionalsToOrder;
import controller.command.order_commands.NewOrder;
import controller.command.order_commands.SetClientToOrder;
import domain.Order;
import domain.Product;

/**
 * Servlet implementation class OrderController
 */
@WebServlet({ "/Order"})
public class OrderController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static CommandFactory cf = CommandFactory.init();
       
    public OrderController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	//Every POST is a new Order
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession(true);
		
		try {
			
			String action = request.getParameter("action");
			
			Command command = cf.getCommand(action);
			
			if(command instanceof NewOrder){			
				((NewOrder) command).setItems(request.getParameterValues("products[]"), request.getParameterValues("prod_quantity[]"));
				command.execute();
				request.setAttribute("order", ((NewOrder)command).getOrder());
				session.setAttribute("order", ((NewOrder)command).getOrder());
				request.setAttribute("additionals", ((NewOrder)command).getAvailableAdditionals());
			} else if(command instanceof SetClientToOrder){
				
			} if(command instanceof AddAdditionalsToOrder){
				Command command2;
				if(session.getAttribute("user")==null){//User not loged, redirect
					command2 = cf.getCommand("doLogin");
					command2.execute();
				} else {
					command2 = cf.getCommand("setClientToOrder");
					command2.execute();
				}
				((AddAdditionalsToOrder) command).setOrder((Order)session.getAttribute("order"));
				((AddAdditionalsToOrder) command).setAdditionalsIds(request.getParameterValues("additional[]"));
				((AddAdditionalsToOrder) command).setReceiving(Integer.valueOf(request.getParameter("receiving")));
				command.execute();
				request.setAttribute("order", ((AddAdditionalsToOrder)command).getOrder());				
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
