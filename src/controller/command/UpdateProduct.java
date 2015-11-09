/**
 * 
 */
package controller.command;

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
		//Check for an image update
        if(product.getImgUrl() != null){
     	   Product oldProduct = dao.getProductById(product.getId());
     	   //Change img
     	   dao.deleteImageProduct(oldProduct);
     	   dao.updateImage(product);
        }
        //Update product
        dao.update(product);
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
