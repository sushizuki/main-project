package controller.command.order_commands;

import java.util.Arrays;
import java.util.HashMap;

import controller.command.Command;
import dao.ProductDAO;
import domain.Order;
import domain.Product;

public class NewOrder implements Command {
	
	private Order order;
	private ProductDAO productDao;
	private String pageToRedirect;
	private String[] productIds;
	private int[] productQuantities;
	
	public NewOrder() {
		super();
		this.productDao = new ProductDAO();
		this.setPageToRedirect("/shopping-cart.jsp");
	}
	
	public void setOrder(Order o){
		this.order = o;
	}
	
	public Order getOrder(){
		return this.order;
	}
	
	public void setPageToRedirect(String pageToRedirect) {
		this.pageToRedirect = pageToRedirect;
	}
	
	public void setProductIds(String[] ids){
		this.productIds = ids;
	}
	
	public void setProductQuantities(String[] qtds){
		int[] array = Arrays.stream(qtds).mapToInt(Integer::parseInt).toArray();
		this.productQuantities = array;
	}
	
	public int[] getProductQuantities(){
		return this.productQuantities;
	}

	@Override
	public void execute() throws Exception {
		setOrder(new Order());
		HashMap<Product,Integer> items = new HashMap<Product,Integer>();
		int count = 0;
	    for(String id:productIds){
	     	Product p = productDao.getProductById(Integer.parseInt(id));
	     	items.put(p, productQuantities[count]);
	     	count++;
	    }
	    this.order.setItems(items);
	    this.order.setTotalPrice();
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
