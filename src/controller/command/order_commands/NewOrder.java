package controller.command.order_commands;

import java.util.HashMap;
import java.util.List;

import controller.command.Command;
import dao.OrderDAO;
import dao.ProductDAO;
import domain.Additional;
import domain.Order;
import domain.Product;

public class NewOrder implements Command {
	
	private Order order;
	private ProductDAO productDao;
	private OrderDAO orderDao;
	private String pageToRedirect;
	private String[] productIds;
	private int[] productQuantities;
	private List<Additional> availabelAdditionals;
	
	public NewOrder() {
		super();
		this.productDao = new ProductDAO();
		this.orderDao = new OrderDAO();
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
		int count = 0;
		int[] array = new int[productIds.length];
		for(String n:qtds){
			try {  
				int i = Integer.parseInt(n);  
				if(i>0){
					array[count] = i;
					count++;
				}
			}catch(NumberFormatException nfe){  }  
		}
		this.productQuantities = array;
	}
	
	public int[] getProductQuantities(){
		return this.productQuantities;
	}
	
	public void setAvailableAdditionals(){
		this.availabelAdditionals = orderDao.getAdditionalsList();
	}
	
	public List<Additional> getAvailableAdditionals(){
		return this.availabelAdditionals;
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
	    this.setAvailableAdditionals();
	}

	@Override
	public String getPageToRedirect() {
		return this.pageToRedirect;
	}

}
