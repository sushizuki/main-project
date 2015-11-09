package controller.command;

import java.util.List;

import dao.ProductDAO;
import domain.Product;

public class ListProducts implements Command {

	private ProductDAO dao;
	private List<Product> products;
	private String pageToRedirect;
	
	public ListProducts() {
		super();
		this.dao = new ProductDAO();
		this.setPageToRedirect("/adm/product-list.jsp");
	}	
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public void execute() throws Exception {
		setProducts(dao.getList());		
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

	public void setPageToRedirect(String pageToRedirect) {
		this.pageToRedirect = pageToRedirect;
	}
	
}
