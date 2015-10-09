package controller;
 
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.UserDAO;
import domain.User;
 
public class UserController extends HttpServlet {
private static final long serialVersionUID = 1L;
private UserDAO dao;
 
public UserController() {
super();
dao = new UserDAO();
}
} 
