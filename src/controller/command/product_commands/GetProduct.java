/** 
*    GetProduct.java to define GetProduct 
*    {purpose} 
*/ 

package controller.command.product_commands;

import java.util.List;

import controller.command.Command;
import dao.ProductDAO;
import domain.Product;

public class GetProduct implements Command {

	private ProductDAO dao;
	private Product product;
	private String pageToRedirect;
	private Integer productId;
	private List<String> productCategories;
	
	public GetProduct(){
		super();
		this.dao = new ProductDAO();
		this.setPageToRedirect("/adm/product.jsp");
	}
	
	public void setProductId(int i){
		this.productId = i;
	}
	
	public void setPageToRedirect(String page){
		this.pageToRedirect = page;
	}
	
	public Product getProduct(){
		return this.product;
	}
	
	public List<String> getProductCategories() {
		return productCategories;
	}
	
	public void setProductCategories(List<String> categoryList) {
		this.productCategories = categoryList;		
	}
	
	@Override
	public void execute() throws Exception {
		this.setProductCategories(dao.getProductCategoryList());
		if(productId != null){ 
			this.product = dao.getProductById(productId);
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
