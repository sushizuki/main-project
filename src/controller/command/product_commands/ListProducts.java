/** 
*    ListProducts.java to define ListProducts 
*    The purpose of this class is to list all products saved in the database 
*/ 

package controller.command.product_commands;

import java.util.List;

import controller.command.Command;
import dao.ProductDAO;
import domain.Product;

public class ListProducts implements Command {

	private ProductDAO productDao;
	private List<Product> productsList;
	private String pageToRedirect;
	
	public ListProducts() {
		super();
		this.productDao = new ProductDAO();
		this.setPageToRedirect("/adm/product-list.jsp"); // Default page to redirect
	}	
	
	public List<Product> getProducts() {
		return productsList;
	}
	
	public void setProducts(List<Product> products) {
		this.productsList = products;
	}

	@Override
	public void execute() throws Exception {
		setProducts(productDao.getList());		
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

	public void setPageToRedirect(String context) {
		
		if(context.contains("/menu")){
			this.pageToRedirect = "menu.jsp";
		} else {
			this.pageToRedirect = "/adm/product-list.jsp";
		}
	}
	
}
