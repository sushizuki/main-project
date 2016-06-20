/**
*    ProductController.java to define ProductController
*    Manages POST and GET requests about Product related operations
*    and shows exceptions handling.
*/

package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

import controller.command.*;
import controller.command.product_commands.*;
import domain.Product;


/**
 * Servlet ProductController
 */
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_PRODUCT_IMAGE_URL = "img/products/default.png";

	/**
	 * Command factory uses Factory and Command design patterns
	 */
    private static CommandFactory commandFactory = CommandFactory.init();

	public ProductController() {
        super();
    }
	
	private Command getCommand(HttpServletRequest request){
		//Get command from request
    	String action = request.getParameter("action");

    	//Default action: List Products
    	if(action == null || action.isEmpty()){
    		action = "listProducts";
    	}else{
    		//Do nothing
    	}

    	Command command = commandFactory.getCommand(action);

    	//Default command: List Products
    	if(command == null){//Invalid command passed, go to default command
    		action = "listProducts";
    		command = commandFactory.getCommand(action);;
    	}else{
    		//Do nothing
    	}
    	
    	return command;
	}

	
	/**
	 * Handle GET requests: do logout.
	 * @param request contains request information for this servlet
	 * @param response sends response information from this servlet to the client
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view = null;

		try {

        	Command command = getCommand(request);

			command.execute();

			// Set retunr atributes for the request in accordance of each command
			if(command instanceof ListProducts){
		 		request.setAttribute("products", ((ListProducts)command).getProducts() );
				((ListProducts) command).setPageToRedirect(request.getRequestURI());
			} else if(command instanceof NewProduct){
				request.setAttribute("categories", ((NewProduct)command).getProductCategories());
			} else if(command instanceof GetProduct){
				((GetProduct)command).setProductId(Integer.parseInt(request.getParameter("id")));
				command.execute();
				request.setAttribute("product", ((GetProduct)command).getProduct());
				request.setAttribute("categories", ((GetProduct)command).getProductCategories());
			} else if(command instanceof DeleteProduct){
				((DeleteProduct)command).setProduct(Integer.parseInt(request.getParameter("id")));
				command.execute();
		        command = commandFactory.getCommand("listProducts");
		        command.execute();
		 		request.setAttribute("products", ((ListProducts)command).getProducts() );
		        request.setAttribute("message", "sucess");
			}else{
				//Do nothing
			}

			//Set redirection
			view = request.getRequestDispatcher(command.getPageToRedirect());

        }catch(Exception e){
			System.err.println("ERROR while processing request: ");
			e.printStackTrace();
			request.setAttribute("message", "failure");
			view = request.getRequestDispatcher("/404.jsp");
        }

		view.forward(request, response);
    }

	//POST for creating or updating a product
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = null;
		RequestDispatcher view = null;

		try {

			Product product = null;

			try {
				//Handle form fields and img upload
				product = processProductForm(request, response);
				System.out.println("Product in form: "+product.getName());

				//Decide whether it's a update or a insert action
				if(product.getId() == 0){ //0 is a null for int variables
					action = "insertProduct";
					System.out.println("action = INSERT Product");
				} else {
					action = "updateProduct";
					System.out.println("action = Update Product");
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
				if(action==null || action.isEmpty()){
					doGet(request, response);
					System.out.println("action null or empty");
					return;
				}
			}

			Command command = commandFactory.getCommand(action);

			if(command instanceof InsertProduct){
				((InsertProduct) command).setProduct(product);
				command.execute();
			} else if(command instanceof UpdateProduct){
				((UpdateProduct) command).setProduct(product);
				command.execute();
				System.out.println("command = Update Product");
			}

			//Set return attributes
			System.out.println("Back to list all products");
	        command = commandFactory.getCommand("listProducts");
	        command.execute();
	 		request.setAttribute("products", ((ListProducts)command).getProducts() );


	 		request.setAttribute("message", "sucess");

			view = request.getRequestDispatcher(command.getPageToRedirect());

        } catch (Exception e) {
			System.err.println("ERROR while retrieving products information: ");
			e.printStackTrace();
			request.setAttribute("message", "failure");
			view = request.getRequestDispatcher("/404.jsp");
        }

		//Redirect
        view.forward(request, response);
   }

	private Product processProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		response.setCharacterEncoding("UTF-8");
		//request.setCharacterEncoding("UTF-8");

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
		Product product = null;
		int id = 0;
		double price = 0;
		String name = null, description = null, imageURL = null, extra = null, category = null;
 	    
		//Try parse form inputs
		try {
			items = upload.parseRequest(request);

			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();		    
			
	 	    while (iter.hasNext()) {
			    FileItem item = iter.next();

		    	// get values from form
			    if (item.isFormField()) {
			    	if(item.getFieldName().equals("name")){
			    		name = item.getString("UTF-8");
			    	}else if(item.getFieldName().equals("description")){
			    		description = item.getString("UTF-8");
			    	}else if(item.getFieldName().equals("price")){
			    		price = Double.valueOf(item.getString("UTF-8"));
			    	}else if(item.getFieldName().equals("category")){
			    		category = item.getString("UTF-8");
			    	}else if(item.getFieldName().equals("imageURL")){
			    		imageURL = item.getString("UTF-8");
			    	//}else if(item.getFieldName().equals("img")){
			    		//imageURL = factory.getRepository()+item.getString("UTF-8");
			    	}else if(item.getFieldName().equals("id")){
			    		try{
			    			id = Integer.parseInt(item.getString("UTF-8"));
			    		}catch(Exception exception){
			    			id = 0;
			    		};
			    	}
			    } else { //if not a regular form field (img upload)
			    	if (imageURL==null && "img".equals(item.getFieldName())) {
			            if (item.getName() == null || item.getName().isEmpty()) {
			                // No file was been selected.
			            	imageURL = DEFAULT_PRODUCT_IMAGE_URL;
			            } else if (item.getSize() == 0) {
			                // it was an empty file.
			            	imageURL = DEFAULT_PRODUCT_IMAGE_URL;
			            }
			            else {
			            	//Save img
			            	String fileName = item.getName();
					 		String uploadDir = contextRoot+"img"+File.separator+"products"+File.separator;
					 		String imgUrl = uploadDir+fileName;

					 		imageURL = "img/products/"+fileName;


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
		} catch (Exception productFormException) {
			productFormException.printStackTrace();
			throw new ServletException("ERROR PROCESSING PRODUCT FORM: "+productFormException.getMessage());
		}
		
		System.out.println("----VALORES DO FORM DE PRODUTO--");
		System.out.println("ID:"+id);
		System.out.println("Name:"+name);
		System.out.println("Description:"+description);
		System.out.println("Price"+price);
		System.out.println("Category:"+category);
		System.out.println("URL:"+imageURL);
	    //Try Instantiate Product
		try {
			if(imageURL==null){
				imageURL = DEFAULT_PRODUCT_IMAGE_URL;
			}
	        if(id == 0){
	        	product = new Product(name, description, price, imageURL, String.valueOf(category), extra);
	        } else {
	        	product = new Product(id, name, description, price, imageURL, String.valueOf(category), extra);
	        }
	        System.out.println("getCategory:"+product.getCategory());
		} catch (Exception productException){
			productException.printStackTrace();
			throw new ServletException("ERROR INSTANTIATING PRODUCT:"+productException.getMessage());
		}
		
 	    return product;
	}
}
