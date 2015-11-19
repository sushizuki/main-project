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
import controller.command.order_commands.NewOrder;
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
				((NewOrder) command).setProductIds(request.getParameterValues("products[]"));
				((NewOrder) command).setProductQuantities(request.getParameterValues("prod_quantity[]"));
				command.execute();
				request.setAttribute("order", ((NewOrder)command).getOrder());
				/*if(session.getAttribute("user")==null){//User not loged, redirect
					command = cf.getCommand("doLogin");
					command.execute();
				}*/
			}
						
			//CHECK IF CLIENT IS LOGGED
			
			//CREATE ORDER WITH PRODUCTS
	
			//DESCRIBE ORDER DETAILS IN PAGE
			
			//GET RECEIVING DETAILS FOR ORDER
			
			//FINALIZE ORDER
			
	 		
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
	
	/*private HashMap<Product, Integer> handleProductList(String[] productIds){
		HashMap<Product, Integer> hashMap = new HashMap<Product, Integer>();
		if (!hashMap.containsKey(product)) {
		    List<Location> list = new ArrayList<Location>();
		    list.add(student);

		    hashMap.put(product, 1);
		} else {
		    hashMap.get(product).;
		}
	}*/
}
