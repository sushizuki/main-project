/** 
*    NewProduct.java to define NewProduct 
*    {purpose} 
*/ 

package controller.command.product_commands;

import java.util.List;

import controller.command.Command;
import dao.ProductDAO;

public class NewProduct implements Command {

	private ProductDAO dao;
	private List<String> productCategories;
	private String pageToRedirect;

	public NewProduct(){
		super();
		this.dao = new ProductDAO();
		this.setPageToRedirect("/adm/product.jsp");
	}
	
	public List<String> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<String> productCategories) {
		this.productCategories = productCategories;
	}

	public void setPageToRedirect(String pageToRedirect) {
		this.pageToRedirect = pageToRedirect;
	}
	
	@Override
	public void execute() throws Exception {
		this.setProductCategories(dao.getProductCategoryList());
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
