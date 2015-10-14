package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {

		//Process login
		//Get form data then try to log in as client, redirect to main page
		//if not able to log as client, try log in as adm
		
		//if adm		
		response.sendRedirect("adm/dashboard.jsp");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Essa Servlet tem que tratar a requisi��o</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Oi mundo Servlet!</h1>");
		out.println("</body>");
		out.println("</html>");
	}
}
