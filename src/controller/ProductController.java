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

	/**
	 * Command factory uses Factory and Command design patterns
	 */
    private static CommandFactory commandFactory = CommandFactory.init();

	public ProductController() {
        super();
    }

	
	/**
	 * Handle GET requests: do logout.
	 * @param request contains request information for this servlet
	 * @param response sends response information from this servlet to the client
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view = null;

		try {

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

				//Decide whether it's a update or a insert action
				if(product.getId() == 0){ //0 is a null for int variables
					action = "insertProduct";
				} else {
					action = "updateProduct";
				}
			}catch(Exception e){
				if(action==null || action.isEmpty()){
					doGet(request, response);
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
			}

			//Set return attributes
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
 	    Product product = null;
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
				 		product.setImageURL(imgUrl);
			    	}else if(item.getFieldName().equals("id")){
			    		try{
			    			id = Integer.parseInt(item.getString("UTF-8"));
			    			product.setId(id);
			    		}catch(Exception e){};
			    	}


			    } else { //if not a regular form field

			    	if ("img".equals(item.getFieldName())) {
			            if (item.getName() == null || item.getName().isEmpty()) {
			                // No file was been selected.
			            	product.setImageURL(null);
			            } else if (item.getSize() == 0) {
			                // No file was been selected, or it was an empty file.
			            	product.setImageURL(null);
			            }
			            else {
			            	//Save img
			            	String fileName = item.getName();
					 		String uploadDir = contextRoot+"img"+File.separator+"products"+File.separator;
					 		String imgUrl = uploadDir+fileName;

					 		product.setImageURL("img/products/"+fileName);


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
			throw new RuntimeException("ERROR PROCESSING PRODUCT FORM: "+e2.getMessage());
		}
	}
}
