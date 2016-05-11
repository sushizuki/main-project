/** 
*    NewProduct.java to define NewProduct 
*    The purpose of this class is open the form for a new product and 
*    searches available categories the product can be saved with 
*/ 

package controller.command.product_commands;

import java.util.List;

import controller.command.Command;
import dao.ProductDAO;

public class NewProduct implements Command {

	private ProductDAO productDao;
	private List<String> productCategoriesList;
	private String pageToRedirect;

	public NewProduct(){
		super();
		this.productDao = new ProductDAO();
		this.setPageToRedirect("/adm/product.jsp");
	}
	
	public List<String> getProductCategories() {
		return productCategoriesList;
	}

	public void setProductCategories(List<String> productCategories) {
		this.productCategoriesList = productCategories;
	}

	public void setPageToRedirect(String pageToRedirect) {
		this.pageToRedirect = pageToRedirect;
	}
	
	@Override
	public void execute() throws Exception {
		this.setProductCategories(productDao.getProductCategoryList());
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
