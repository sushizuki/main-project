package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private static String UPLOAD_IMG_DIR = "img"+File.separator+"products";
	
	public ProductController() {
        super();
        dao = new ProductDAO();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
		String forward="";
        String action = request.getParameter("action");
        
        //Default action: List products
        if(action == null){
        	action = "listProducts";
        } if (action.equalsIgnoreCase("listProducts") || action.isEmpty()){
            forward = LIST_PRODUCT;
            try {
				request.setAttribute("products", dao.getList());
			} catch (SQLException e) {
		    	System.err.println("ERROR while retrieving products information: ");
				e.printStackTrace();
			}
        } else if (action.equalsIgnoreCase("delete")){
            int productId = Integer.parseInt(request.getParameter("id"));
            Product product = dao.getProductById(productId);
            dao.delete(product);
            forward = LIST_PRODUCT;
            try {
				request.setAttribute("products", dao.getList());
		        request.setAttribute("message", "sucess");
			} catch (Exception e) {
		    	System.err.println("ERROR while retrieving products information: ");
				e.printStackTrace();
			}    
        } else if (action.equalsIgnoreCase("update")){
            forward = INSERT_OR_EDIT;
            int productId = Integer.parseInt(request.getParameter("id"));
            Product product = dao.getProductById(productId);
            request.setAttribute("product", product);
        } else {
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Handle form fields and img upload
		Product product = processProductForm(request, response);
		if(product == null){
            request.setAttribute("message", "failure");
			return;
		}
		
		//Decide whether it's a update or a insert action
       if(product.getId() == 0){ //0 is a null for int variables
           dao.insert(product);
           request.setAttribute("message", "sucess");
       }
       else{ //Update Product
    	   
    	   //Check for an image update
           if(product.getImgUrl() != null){
        	   Product oldProduct = dao.getProductById(product.getId());
        	   //Change img
        	   dao.deleteImageProduct(oldProduct);
        	   dao.updateImage(product);
           }
           dao.update(product);
           request.setAttribute("message", "sucess");
       }
       
       //Redirect and set return attributes              
       RequestDispatcher view = request.getRequestDispatcher(LIST_PRODUCT);
       
       try {
			request.setAttribute("products", dao.getList());
       } catch (SQLException e) {
    	   System.err.println("ERROR while retrieving products information: ");
    	   e.printStackTrace();
       }
       
       view.forward(request, response);
   }
	
	private Product processProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");  
		
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Set factory constraints
		factory.setSizeThreshold(10240000); //10MB
		String contextRoot = request.getSession().getServletContext().getRealPath("/");
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

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
			    	int id, category;
			    	double price;
			    	
			    	if(item.getFieldName().equals("name")){
			    		name = item.getString("UTF-8");
				 		product.setName(name);
			    	}else if(item.getFieldName().equals("description")){
			    		description = item.getString("UTF-8");
				 		product.setDescription(description);
			    	}else if(item.getFieldName().equals("price")){
			    		price = Double.valueOf(item.getString("UTF-8"));
				 		product.setPrice(price);
			    	}else if(item.getFieldName().equals("category")){
			    		category = Integer.parseInt(item.getString("UTF-8"));
				 		product.setCategory(category);
			    	}else if(item.getFieldName().equals("img")){
			    		imgUrl = factory.getRepository()+item.getString("UTF-8");
				 		product.setImgUrl(imgUrl);
			    	}else if(item.getFieldName().equals("id")){
			    		try{
			    			id = Integer.parseInt(item.getString("UTF-8"));
			    			product.setId(id);
			    		}catch(Exception e){System.err.println("empty ID: inserting new product...");};
			    	}		    	
			           
			 		
			    } else { //if not regular form field
			    	
			    	if ("img".equals(item.getFieldName())) {
			            if (item.getName() == null || item.getName().isEmpty()) {
			                // No file was been selected.
			            	product.setImgUrl(null);
			            } else if (item.getSize() == 0) {
			                // No file was been selected, or it was an empty file.
			            	product.setImgUrl(null);
			            }
			            else {
			            	//Save img
			            	String fileName = item.getName();
					 		String uploadDir = contextRoot+UPLOAD_IMG_DIR+File.separator;
					 		String imgUrl = uploadDir+fileName;		
			            	
					 		product.setImgUrl("img/products/"+fileName);
			            	
					 		
					 		//Create product images directory if not exists					        
					 		if(Files.notExists(Paths.get(uploadDir.substring(0, uploadDir.length()-1)))){
					 			
					 			new File(uploadDir.substring(0, uploadDir.length()-1)).mkdirs();
					 		}
					        
					 		File saveFile = new File(imgUrl);
					        
					        saveFile.createNewFile();
					        item.write(saveFile);
			            }
			        }			    	
			    }
			}
	 	    return product;
		} catch (Exception e2) {
			System.err.println("ERROR while processing form: ");
			e2.printStackTrace();
			return null;
		}        	   	
	}
}
