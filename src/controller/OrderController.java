package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.CommandFactory;

/**
 * Servlet implementation class OrderController
 */
@WebServlet({ "/OrderController", "/shopping-cart" })
public class OrderController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static CommandFactory cf = CommandFactory.init();
       
    public OrderController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
