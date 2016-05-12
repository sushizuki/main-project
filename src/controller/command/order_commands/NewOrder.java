/** 
*    NewOrder.java to define NewOrder 
*    The purpose of this class is to initialize the order
*    with the items selected at the menu
*/ 

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
	
	private int[] castProductQuantities(int size, String[] quantity){
		int counter = 0;
		int[] arrayQuantity = new int[size];
		for(String idProducts:quantity){
			try {  
				int i = Integer.parseInt(idProducts);  
				if(i>0){
					arrayQuantity[counter] = i;
					counter++;
				}
			}catch(NumberFormatException nfe){  }  
		}
		return arrayQuantity;
	}

	@Override
	public void execute() throws Exception {
		this.order = new Order();
		this.setItemsToOrder();
	    this.order.setTotalPrice();
	}
	
	public void setItems(String[] products, String[]quantity){
		int[] quantities = castProductQuantities(products.length, quantity);
		HashMap<Product,Integer> items = new HashMap<Product,Integer>();
		int counter = 0;
	    for(String idProducts:products){
	     	Product product = productDao.getProductById(Integer.parseInt(idProducts));
	     	items.put(product, quantities[counter]);
	     	counter++;
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
