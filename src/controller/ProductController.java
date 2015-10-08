package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
	}
	
	private void createProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		// busca o writer
        PrintWriter out = response.getWriter();
                        
        // get parameters from request
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.valueOf(request.getParameter("price"));
        String imgUrl = request.getParameter("imgUrl");        
           
        // create object
        Product product = new Product(name, description, price, imgUrl);
        
        // save object
        ProductDAO dao = new ProductDAO();
        dao.insert(product);
        
        // imprime o nome do contato que foi adicionado
        out.println("<html>");
        out.println("<body>");
        out.println("Product " + product.getName() +
                " adicionado com sucesso");
        out.println("</body>");
        out.println("</html>");
	}

}
