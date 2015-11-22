package controller.command.order_commands;

import java.util.HashMap;

import controller.command.Command;
import dao.ProductDAO;
import domain.Order;
import domain.Product;

public class NewOrder implements Command {
	
	private Order order;
	private ProductDAO productDao;
	private String pageToRedirect;
	private HashMap<Product, Integer> itemsToOrder;
	
	public NewOrder() {
		super();
		this.productDao = new ProductDAO();
		this.pageToRedirect = "/shopping-cart.jsp";
	}
		
	public Order getOrder(){
		return this.order;
	}
	
	private int[] castProductQuantities(int size, String[] qtds){
		int count = 0;
		int[] array = new int[size];
		for(String n:qtds){
			try {  
				int i = Integer.parseInt(n);  
				if(i>0){
					array[count] = i;
					count++;
				}
			}catch(NumberFormatException nfe){  }  
		}
		return array;
	}

	@Override
	public void execute() throws Exception {
		this.order = new Order();
		this.setItemsToOrder();
	    this.order.setTotalPrice();
	}
	
	public void setItems(String[] products, String[]qtds){
		int[] quantities = castProductQuantities(products.length, qtds);
		HashMap<Product,Integer> items = new HashMap<Product,Integer>();
		int count = 0;
	    for(String id:products){
	     	Product p = productDao.getProductById(Integer.parseInt(id));
	     	items.put(p, quantities[count]);
	     	count++;
	    }
	    this.itemsToOrder = items;
	}

	private void setItemsToOrder() {
		this.order.setItems(itemsToOrder);
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
