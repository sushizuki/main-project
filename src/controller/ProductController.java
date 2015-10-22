package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.ProductDAO;
import domain.Product;

public class ProductController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ProductDAO dao;
    private static String INSERT_OR_EDIT = "/adm/product.jsp";
    private static String LIST_PRODUCT = "/adm/product-list.jsp";
    private static String UPLOAD_IMG_DIR = "img\\products";
	
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
				request.setAttribute("imgDir", System.getProperty("user.dir"));
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
				request.setAttribute("imgDir", System.getProperty("user.dir"));
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
		
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Set factory constraints
		factory.setSizeThreshold(10240000); //10MB
		String contextRoot = request.getServletContext().getRealPath("/");
		factory.setRepository(new File(contextRoot + "WEB-INF/tmp"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(1024000); //1MB

		// Parse the request
		List<FileItem> items;
		
		// create object
 	    Product product = new Product();
		try {
			items = upload.parseRequest(request);
			
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
						
	 	    while (iter.hasNext()) {
			    FileItem item = iter.next();

			    if (item.isFormField()) {		    	
			    	// get parameters from request
			    	String name, description, imgUrl;
			    	int category;
			    	double price;
			    	
			    	if(item.getFieldName().equals("name")){
			    		name = item.getString();
				 		product.setName(name);
			    	}else if(item.getFieldName().equals("description")){
			    		description = item.getString();
				 		product.setDescription(description);
			    	}else if(item.getFieldName().equals("price")){
			    		price = Double.valueOf(item.getString());
				 		product.setPrice(price);
			    	}else if(item.getFieldName().equals("category")){
			    		category = Integer.parseInt(item.getString());
				 		product.setCategory(category);
			    	}else if(item.getFieldName().equals("img")){
			    		imgUrl = factory.getRepository()+item.getString();
				 		product.setImgUrl(imgUrl);
			    	}		    	
			           
			 		
			    } else { //if not form field
			    	
			    	String fileName = item.getName();
			 		product.setImgUrl(fileName);   
			        
			        File saveFile = new File(contextRoot+UPLOAD_IMG_DIR+File.separator+fileName);
			        
			        saveFile.createNewFile();
			        item.write(saveFile);
			    }
			}	
		} catch (Exception e2) {
			e2.printStackTrace();
			return;
		}        	   	
       
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
			request.setAttribute("imgDir", contextRoot+UPLOAD_IMG_DIR+File.separator);
       } catch (SQLException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
       }
       
       view.forward(request, response);
   }

}
