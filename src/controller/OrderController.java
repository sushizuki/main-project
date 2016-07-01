/**
*    OrderController.java to define OrderController
*    Manages POST and GET requests about Order related operations
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

import controller.command.*;
import controller.command.order_commands.*;
import controller.command.user_commands.CheckUserLogged;
import domain.Client;
import domain.Order;

/**
 * Servlet OrderController
 */
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Command factory uses Factory and Command design patterns
	 */
	private static CommandFactory commandFactory = CommandFactory.init();

	public OrderController() {
		super();
	}

	/**
	 * Handle GET requests: do logout.
	 * @param request contains request information for this servlet
	 * @param response sends response information from this servlet to the client
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		RequestDispatcher view = null;
		HttpSession session = request.getSession(true);

		try {

        	//Get command from request
			String action = request.getParameter("action");

			//Default action: List Products
			if(action == null || action.isEmpty()){
				action = "getOrderList";
			}else{
				//Nothing to do
			}

			Command command = commandFactory.getCommand(action);

			if(command instanceof GetOrder){
				((GetOrder) command).setSession(session);
				command.execute();

				Command command2 = commandFactory.getCommand("getAvailableAdditionals");
				command2.execute();
				request.setAttribute("additionals", ((GetAvailableAdditionals)command2).getAdditionals());
			}else if(command instanceof GetOrderList){
				((GetOrderList) command).setSession(session);
				command.execute();
				request.setAttribute("orders", ((GetOrderList) command).getOrderList());
				request.setAttribute("user", session.getAttribute("user"));
			}

			view = request.getRequestDispatcher(command.getPageToRedirect());

		} catch (Exception e) {
			System.err.println("ERROR while recovering order: ");
			e.printStackTrace();
			request.setAttribute("message", "failure");
			view = request.getRequestDispatcher("/404.jsp");
		}

		//Redirect
		try {
			view.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Handle POST requests: creating, updating a user or do login.
	 * @param request contains request information for this servlet
	 * @param response sends response information from this servlet to the client
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		RequestDispatcher view = null;
		HttpSession session = request.getSession(true);

		try {

			String action = request.getParameter("action");

			//Default action
			if(action == null || action.isEmpty()){
				action = "getOrder";
			}

			Command command = commandFactory.getCommand(action);

			if(command instanceof GetOrder){
				((GetOrder) command).setSession(session);
				command.execute();
				request.setAttribute("order", ((GetOrder) command).getOrder());
				session.setAttribute("order", ((GetOrder)command).getOrder());

				Command command2 = commandFactory.getCommand("getAvailableAdditionals");
				command2.execute();
				request.setAttribute("additionals", ((GetAvailableAdditionals)command2).getAdditionals());
			} else if(command instanceof NewOrder){
				((NewOrder) command).setItems(request.getParameterValues("products[]"), request.getParameterValues("prod_quantity[]"));
				command.execute();
				request.setAttribute("order", ((NewOrder)command).getOrder());
				session.setAttribute("order", ((NewOrder)command).getOrder());

				command = commandFactory.getCommand("getAvailableAdditionals");
				command.execute();
				request.setAttribute("additionals", ((GetAvailableAdditionals)command).getAdditionals());
			} if(command instanceof AddAdditionalsToOrder){
				Command command2;
				command2 = commandFactory.getCommand("checkUserLogged");
				((CheckUserLogged)command2).setSession(session);

				//IF user was not logged
				if( ((CheckUserLogged)command2).getUser() != null ){
					view = request.getRequestDispatcher(command2.getPageToRedirect());
					view.forward(request, response);
					return;
				}

				command2 = commandFactory.getCommand("setClientToOrder");
				((SetClientToOrder)command2).setOrder((Order)session.getAttribute("order"));
				((SetClientToOrder)command2).setClient((Client)session.getAttribute("user"));
				command2.execute();
				session.setAttribute("order", ((SetClientToOrder)command2).getOrder());
				request.setAttribute("order", ((SetClientToOrder)command2).getOrder());


				((AddAdditionalsToOrder) command).setOrder((Order)session.getAttribute("order"));
				((AddAdditionalsToOrder) command).setAdditionalsIds(request.getParameterValues("additional[]"));
				String change = request.getParameter("change");
				//Payment by card
				if(change.isEmpty()){
					change = "0";
				}
				((AddAdditionalsToOrder) command).setPayment(request.getParameter("payment"),
				((Order)session.getAttribute("order")).getTotalPrice(),
				Double.valueOf(change) );

				((AddAdditionalsToOrder) command).setReceiving(Integer.valueOf(request.getParameter("receiving")));
				command.execute();
				request.setAttribute("order", ((AddAdditionalsToOrder)command).getOrder());
				session.setAttribute("order", ((AddAdditionalsToOrder)command).getOrder());
			} else if(command instanceof SetCollectTime){
				((SetCollectTime)command).setOrder((Order)session.getAttribute("order"));
				((SetCollectTime)command).setCollectTime(request.getParameter("date"), request.getParameter("time"));
				command.execute();
			} else if(command instanceof SetDeliveryDetails){
				((SetDeliveryDetails)command).setOrder((Order)session.getAttribute("order"));
				((SetDeliveryDetails)command).setCollectTime(request.getParameter("date"), request.getParameter("time"));
				((SetDeliveryDetails)command).setAddress(((Client)session.getAttribute("user")).getAddress());
				request.setAttribute("order", ((SetDeliveryDetails)command).getOrder());
				session.setAttribute("order", ((SetDeliveryDetails)command).getOrder());
				command.execute();
			} else if(command instanceof SaveOrder){
				((SaveOrder)command).setOrder( (Order)session.getAttribute("order") );
				command.execute();
				session.setAttribute("order", null);
			}

			view = request.getRequestDispatcher(command.getPageToRedirect());

		} catch (Exception e) {
			System.err.println("ERROR while creating order: ");
			e.printStackTrace();
			request.setAttribute("message", "failure");
			view = request.getRequestDispatcher("/404.jsp");
		}

		//Redirect
		try {
			view.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
