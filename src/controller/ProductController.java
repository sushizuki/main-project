package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import domain.Product;

public class ProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ProductDAO dao;
    private static String INSERT_OR_EDIT = "/adm/product.jsp";
    private static String LIST_PRODUCT = "/adm/product-list.jsp";
	
	public ProductController() {
        super();
        dao = new ProductDAO();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");  
        
		String forward="";
        String action = request.getParameter("action");
        if(action == null){
        	action="listProducts";
        }

        if (action.equalsIgnoreCase("delete")){
            int productId = Integer.parseInt(request.getParameter("id"));
            dao.delete(productId);
            forward = LIST_PRODUCT;
            try {
				request.setAttribute("products", dao.getList());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
        } else if (action.equalsIgnoreCase("update")){
            forward = INSERT_OR_EDIT;
            int productId = Integer.parseInt(request.getParameter("id"));
            Product product = dao.getProductById(productId);
            request.setAttribute("product", product);
        }else if (action.equalsIgnoreCase("listProducts") || action.isEmpty()){
            forward = LIST_PRODUCT;
            try {
				request.setAttribute("products", dao.getList());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");  
		
   	 // get parameters from request
       String name = request.getParameter("name");
       String description = request.getParameter("description");
       double price = Double.valueOf(request.getParameter("price"));
       String imgUrl = request.getParameter("img");
       String category = request.getParameter("category");
          
       // create object
       Product product = new Product(name, description, price, imgUrl, category);
   	
       
       String productId = request.getParameter("id");
       if(productId == null || productId.isEmpty()){
           dao.insert(product);
       }
       else{
           product.setId(Integer.parseInt(request.getParameter("id")));
           dao.update(product);
       }
       
       RequestDispatcher view = request.getRequestDispatcher(LIST_PRODUCT);
       
       try {
			request.setAttribute("products", dao.getList());
       } catch (SQLException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
       }
       
       view.forward(request, response);
   }

}
