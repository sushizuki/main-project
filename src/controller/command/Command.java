/** 
*    Command.java to define Command 
*    {purpose} 
*/ 

package controller.command;

public interface Command {
	public void execute() throws Exception;
	public String getPageToRedirect();
}
