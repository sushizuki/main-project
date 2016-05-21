/** 
*    Command.java to define Command 
*    Here,  It's defined a common interface to order,
*    products, and users objects commands that uses Command design patterns.
*/ 

package controller.command;

public interface Command {
	
	/**
	 * Method signature (interface) for executing any command
	 *
	 */
	public void execute() throws Exception;
	
	
	/**
	 * Method signature (interface) to define that every command must assign a redirection page
	 *@return String of the default page to redirect
	 */
	public String getPageToRedirect();
}
