package controller.command.product_commands;

import controller.command.Command;
import dao.ProductDAO;
import domain.Product;

public class DeleteProduct implements Command {

	private ProductDAO dao;
	private String pageToRedirect;
	private Product product;
	
	public DeleteProduct() {
		super();
		this.dao = new ProductDAO();
		this.setPageToRedirect("/adm/product-list.jsp");
	}
	
	public void setPageToRedirect(String pageToRedirect) {
		this.pageToRedirect = pageToRedirect;
	}
	
	public void setProduct(Integer productId){
		this.product = dao.getProductById(productId);
	}

	@Override
	public void execute() throws Exception {
		if(this.product != null){
			dao.delete(product);
		}
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
