package controller;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDAO;
import domain.Product;
import domain.User;
 
public class UserController extends HttpServlet {
private static final long serialVersionUID = 1L;
private UserDAO dao;
 
public UserController() {

	
	//Choose action then forward to right method
			createUser(request, response);
		}
		
		private void createUser(HttpServletRequest request,
				HttpServletResponse response) throws IOException{
			// busca o writer
	        PrintWriter out = response.getWriter();  
	                        
	        // get parameters from request
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String address = request.getParameter("address");
	        String password = request.getParameter("Password");
	        String phone = request.getParameter("Phone");        
	           
	        // create object
	        User user = new User(name, email, address, password, phone);
	        
	        // save object
	       UserDAO dao = new UserDAO();
	        dao.insert(user);
	        
	        // imprime o nome do contato que foi adicionado
	        out.println("<html>");
	        out.println("<body>");
	        out.println("Product " + product.getDescription() +
	                " adicionado com sucesso");
	        out.println(request.getParameter("description"));
	        out.println("</body>");
	        out.println("</html>");
		}

	}

}
} 
