package dao;

import java.sql.SQLException;

import domain.Additional;
import domain.Order;
import domain.Product;

public class Teste {

	public Teste() {
		
	}

	public static void main(String[] args) {
		OrderDAO dao = new OrderDAO();
		System.out.println("INICIOU");

		try {
			for(Order o : dao.getList()){
				System.out.println("Order ID: "+o.getId());
				System.out.println("Total Price: "+o.getTotalPrice());
				for(Additional a : o.getAdditionals()){
					System.out.println("\tAdditional: "+a.getName());
				}
				System.out.println("Client: "+o.getClient().getName());
				for(Product p : o.getItems().keySet()){
					System.out.println("\tProduct: "+p.getName());					
				}
				System.out.println("Endereço: "+o.getReceiving().getAddress().getAddress());
				System.out.println("Pagamento: "+o.getPayment().getPaymentType());				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
