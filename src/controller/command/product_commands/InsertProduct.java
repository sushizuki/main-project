/** 
*    InsertProduct.java to define InsertProduct 
*    The purpose of this class is to insert a new product into the database.
*/ 

package controller.command.product_commands;

import controller.command.Command;
import dao.ProductDAO;
import domain.Product;

public class InsertProduct implements Command{

	private Product product;	
	private ProductDAO productDao;
	private String pageToRedirect;
	
	public InsertProduct() {
		super();
		this.productDao = new ProductDAO();
		this.setPageToRedirect("/adm/product-list.jsp"); //Default page to redirect
	}
	
	public void setProduct(Product product){
		this.product = product;
	}	

	public void setPageToRedirect(String pageToRedirect) {
		this.pageToRedirect = pageToRedirect;
	}

	@Override
	public void execute() {
		if(this.product != null){
			productDao.insert(product);
		}else{
			//Do nothing
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}	
}
