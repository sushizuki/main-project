package controller.command;

import java.util.HashMap;

import controller.command.product_commands.DeleteProduct;
import controller.command.product_commands.GetProduct;
import controller.command.product_commands.InsertProduct;
import controller.command.product_commands.ListProducts;
import controller.command.product_commands.NewProduct;
import controller.command.product_commands.UpdateProduct;

public class CommandFactory {
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
	
	/* Factory pattern */
	public static CommandFactory init() {
		CommandFactory cf = new CommandFactory();
		
		//Product commands
		cf.addCommand("insertProduct", new InsertProduct());
		cf.addCommand("listProducts", new ListProducts());
		cf.addCommand("newProduct", new NewProduct());
		cf.addCommand("updateProduct", new UpdateProduct());
		cf.addCommand("getProduct", new GetProduct());
		cf.addCommand("deleteProduct", new DeleteProduct());
		
		return cf;
	}
}
