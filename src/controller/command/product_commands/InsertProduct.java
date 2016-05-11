/** 
*    InsertProduct.java to define InsertProduct 
*    {purpose} 
*/ 

package controller.command.product_commands;

import controller.command.Command;
import dao.ProductDAO;
import domain.Product;

public class InsertProduct implements Command{

	private Product product;	
	private ProductDAO dao;
	private String pageToRedirect;
	
	public InsertProduct() {
		super();
		this.dao = new ProductDAO();
		this.setPageToRedirect("/adm/product-list.jsp");
	}
	
	public void setProduct(Product p){
		this.product = p;
	}	

	public void setPageToRedirect(String pageToRedirect) {
		this.pageToRedirect = pageToRedirect;
	}

	@Override
	public void execute() {
		if(this.product != null){
			dao.insert(product);
			//dao.finalize();
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}	
}
