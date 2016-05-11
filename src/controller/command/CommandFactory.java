/** 
*    CommandFactory.java to define CommandFactory 
*    Class to define structure for creating commands 
*    initializing them (See Factory design pattern).
*/ 

package controller.command;

import java.util.HashMap;

import controller.command.product_commands.*;
import controller.command.order_commands.*;
import controller.command.user_commands.*;

public class CommandFactory {
	
	/**
	 * Map to define a name (key) for every command, 
	 * this is the way that every command will be identified*/
	private final HashMap<String, Command>	commands;
	
	private CommandFactory() {
		this.commands = new HashMap<>();
	}

	public void addCommand(String name, Command command) {
		this.commands.put(name, command);
	}
	
	public Command getCommand(String name) {
		if ( this.commands.containsKey(name) ) {
			return this.commands.get(name);
		}
		return null;
	}

	public void listCommands() {
		// using stream (Java 8)
		System.out.println("Commands enabled :");
		this.commands.keySet().stream().forEach(System.out::println);
	}
	
	/** Factory pattern **/
	public static CommandFactory init() {
		
		CommandFactory commmandFactory = new CommandFactory();
		
		//Product commands
		commmandFactory.addCommand("insertProduct", new InsertProduct());
		commmandFactory.addCommand("listProducts", new ListProducts());
		commmandFactory.addCommand("newProduct", new NewProduct());
		commmandFactory.addCommand("updateProduct", new UpdateProduct());
		commmandFactory.addCommand("getProduct", new GetProduct());
		commmandFactory.addCommand("deleteProduct", new DeleteProduct());
		
		//Order commands
		commmandFactory.addCommand("newOrder", new NewOrder());
		commmandFactory.addCommand("addAdditionals", new AddAdditionalsToOrder());
		commmandFactory.addCommand("getOrder", new GetOrder());
		commmandFactory.addCommand("getOrderList", new GetOrderList());
		commmandFactory.addCommand("getAvailableAdditionals", new GetAvailableAdditionals());
		commmandFactory.addCommand("setClientToOrder", new SetClientToOrder());
		commmandFactory.addCommand("setCollectTime", new SetCollectTime());
		commmandFactory.addCommand("setDeliveryDetails", new SetDeliveryDetails());
		commmandFactory.addCommand("saveOrder", new SaveOrder());
		
		//User commands
		commmandFactory.addCommand("doLogin", new DoLogin());
		commmandFactory.addCommand("doLogout", new DoLogout());
		commmandFactory.addCommand("checkUserLogged", new CheckUserLogged());
		
		return commmandFactory;
	}
}
