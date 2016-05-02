/** 
*    UpdateProduct.java to define UpdateProduct 
*    {purpose} 
*/ 

/**
 * 
 */
package controller.command.product_commands;

import controller.command.Command;
import dao.ProductDAO;
import domain.Product;

/**
 * @author Allan
 *
 */
public class UpdateProduct implements Command {

	private ProductDAO dao;
	private Product product;
	private String pageToRedirect;
	
	public UpdateProduct(){
		this.dao = new ProductDAO();
		this.setPageToRedirect("/adm/product-list.jsp");
	}
	
	public void setProduct(Product product){
		this.product = product;
	}
	
	public void setPageToRedirect(String page) {
		this.pageToRedirect = page;
	}
	
	@Override
	public void execute() throws Exception {
        dao.update(product);
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
