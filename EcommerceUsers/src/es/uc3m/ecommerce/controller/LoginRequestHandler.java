package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.controller.DataStore;

public class LoginRequestHandler implements RequestHandler {
	

    public LoginRequestHandler() {
    	    	
    }

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext appContext) throws ServletException, IOException {
			
			
			String viewURL = null; 
			new DataStore();
			// If credentials introduced, retrieve form data and BD data
			DataStore bd = (DataStore) appContext.getAttribute("db");
			
			String introducedEmail  = request.getParameter("register_email");
			String introducedPassword = request.getParameter("register_password");
			
			if(bd.containsInfo(introducedEmail) == false) {
				// If no data has been found we should redirect to "register.jsp" with a note
				request.setAttribute("notRegisteredError", 1);
				viewURL = "login.jsp";
				System.out.println("Email not registered");

			}
			else {
				UserDataModelBean userData = bd.getInfo(introducedEmail);
				// If data found check that email and password match
				if(userData.getPassword().equals(introducedPassword)) {
					// Redirect to "index.jsp" and check there if there's a user logged in 
					// -> is session attribute 'user' null?
					HttpSession session = request.getSession();
					session.setAttribute("user", userData);
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
			}
			
		// Return name of page to redirect to
		return viewURL;
		
	}
}
