
/** 
*    GetProduct.java to define GetProduct 
*    The purpose of this class is to get a product with a provided id 
*/ 

package controller.command.product_commands;

import java.util.List;

import controller.command.Command;
import dao.ProductDAO;
import domain.Product;

public class GetProduct implements Command {

	private ProductDAO productDao;
	private Product product;
	private String pageToRedirect;
	private Integer productId;
	private List<String> productCategoriesList;
	
	public GetProduct(){
		super();
		this.productDao = new ProductDAO();
		this.setPageToRedirect("/adm/product.jsp"); //Default page to redirect
	}
	
	public void setProductId(int productId){
		this.productId = productId;
	}
	
	public void setPageToRedirect(String page){
		this.pageToRedirect = page;
	}
	
	public Product getProduct(){
		return this.product;
	}
	
	public List<String> getProductCategories() {
		return productCategoriesList;
	}
	
	public void setProductCategories(List<String> categoryList) {
		this.productCategoriesList = categoryList;		
	}
	
	@Override
	public void execute() throws Exception {
		try{
			this.setProductCategories(productDao.getProductCategoryList());
		
			if(productId != null){ 
				this.product = productDao.getProductById(productId);
			}else{
				//Do nothing
			}
		}catch(Exception executeException){
			executeException.printStackTrace();
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
