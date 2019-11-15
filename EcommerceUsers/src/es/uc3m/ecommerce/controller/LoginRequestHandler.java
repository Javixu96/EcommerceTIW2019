package es.uc3m.ecommerce.controller;

import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.*;


public class LoginRequestHandler implements IHandler {
	
		
    public LoginRequestHandler() {
    	
    }

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String viewURL = null; 
			HttpSession session = request.getSession();

			// LOG OUT -> If there's a user already logged in, then log out and redirect to log in
			if(session.getAttribute("user") != null) {
				System.out.println("user logged in - logging out");
				session.invalidate();
				viewURL = "login.jsp";
				
			} else {
				
				String introducedEmail  = request.getParameter("register_email");
				String introducedPassword = request.getParameter("register_password");
				System.out.println("The email you tried to access: " + introducedEmail);
				System.out.println("The password you used: " + introducedPassword);


				UserManager manager = new UserManager();
				
				List<Appuser> userListByName = manager.findByEmail(introducedEmail);
				System.out.println("he size of the list of users under that email: " + userListByName.size());
				if(userListByName.size() != 0) {
					
					Appuser u = userListByName.get(0);
					
					// If data found check that email and password match
					if(u.getPw().equals(introducedPassword)) {

						// Redirect to "index.jsp" and check there if there's a user logged in 
						// -> is session attribute 'user' null
						session.setAttribute("user", u);
						// Redirect to index.jsp and add personalized elements if user logged in -> JS
						viewURL = "index.jsp";
						System.out.println("User in DB -> redirecting to index.jsp with Dynamic JS");
						
					} else {
						// Redirect to "login.jsp" with alert saying wrong credentials -> JS
						// loginError = 1, email is registered but password is incorrect
						request.setAttribute("passwordError", 1);
						viewURL = "login.jsp";
						System.out.println("Password error");
	
					}
				} else {
					// Email not registered -> redirect to register.jsp with a note
					request.setAttribute("notRegisteredError", 1);
					viewURL = "login.jsp";
					System.out.println("Email not registered");
				}
			}
		// Return name of page to redirect to
		return viewURL;
		
	}

}
