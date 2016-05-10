/** 
*    Command.java to define Command 
*    Here,  It's defined a common interface to order,
*    products, and users objects commands that uses Command design patterns.
*/ 

package controller.command;

public interface Command {
	
	public void execute() throws Exception;
	
	public String getPageToRedirect();
}
