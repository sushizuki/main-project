/** 
*    UpdateProduct.java to define UpdateProduct 
*    The purpose of this class is to update product info
*/ 

package controller.command.product_commands;

import controller.command.Command;
import dao.ProductDAO;
import domain.Product;

public class UpdateProduct implements Command {

	private ProductDAO productDao;
	private Product product;
	private String pageToRedirect;
	
	public UpdateProduct(){
		this.productDao = new ProductDAO();
		this.setPageToRedirect("/adm/product-list.jsp"); //Default page to redirect
	}
	
	public void setProduct(Product product){
		this.product = product;
	}
	
	public void setPageToRedirect(String page) {
		this.pageToRedirect = page;
	}
	
	@Override
	public void execute() throws Exception {
		try{
			productDao.update(product);
		}catch(Exception executeException){
			executeException.printStackTrace();
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
