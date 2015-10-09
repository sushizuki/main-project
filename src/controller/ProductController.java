package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import domain.Product;

public class ProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
				
		//Choose action then forward to right method
		//if(request.getParameter("action").equalsIgnoreCase("list"))
		createProduct(request, response);
	}
	
	private void createProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		// busca o writer
        PrintWriter out = response.getWriter();  
                        
        // get parameters from request
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.valueOf(request.getParameter("price"));
        String imgUrl = request.getParameter("img");        
           
        // create object
        Product product = new Product(name, description, price, imgUrl);
        
        // save object
        ProductDAO dao = new ProductDAO();
        dao.insert(product);
        
        // imprime o nome do contato que foi adicionado

		response.sendRedirect("products.jsp");
	}
	
	public static ArrayList<Product> getList() throws SQLException{
		ProductDAO dao = new ProductDAO();
        return dao.getList();
	}

}
