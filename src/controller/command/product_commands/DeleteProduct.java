/** 
*    DeleteProduct.java to define DeleteProduct 
*    The purpose of this class is to delete a product from database. 
*/ 

package controller.command.product_commands;

import controller.command.Command;
import dao.ProductDAO;
import domain.Product;

public class DeleteProduct implements Command {

	private ProductDAO productDao;
	private String pageToRedirect;
	private Product productToDelete;
	
	public DeleteProduct() {
		super();
		this.productDao = new ProductDAO();
		this.setPageToRedirect("/adm/product-list.jsp"); // Default page to redirect
	}
	
	public void setPageToRedirect(String pageToRedirect) {
		this.pageToRedirect = pageToRedirect;
	}
	
	public void setProduct(Integer productId){
		this.productToDelete = productDao.getProductById(productId);
	}

	@Override
	public void execute() throws Exception {
		try{
			if(this.productToDelete != null){
				productDao.delete(productToDelete);
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
