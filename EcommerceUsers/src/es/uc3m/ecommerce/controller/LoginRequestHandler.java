package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.ecommerce.controller.DataStore;

public class LoginRequestHandler implements RequestHandler {
	

    public LoginRequestHandler() {
    	    	
    }

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext appContext) throws ServletException, IOException {
			
			Object introducedEmailObject = request.getParameter("email");
			Object introducedPasswordObject = request.getParameter("password");
			
			String viewURL = null; 
		
			// If credentials introduced, retrieve form data and BD data
			DataStore bd = (DataStore) appContext.getAttribute("bd");
			
			String introducedEmail = (String) introducedEmailObject;
			String introducedPassword = (String) introducedPasswordObject;
			
			UserDataModelBean userData = bd.getInfo(introducedEmail);
			
			if(userData == null) {
			    // If no data has been found we should redirect to "register.jsp"
				viewURL = "register.jsp";
			}
			else {
				// If data found check that email and password match
				if(userData.getPassword().equals(introducedPassword)) {
					// Redirect to "welcome.jsp"
					viewURL = "welcome.jsp";
					
				} else {
					// Redirect to "wrongCredentials.jsp"
					viewURL = "wrongCredentials.jsp";
				}
			}
			
			
		// Return name of page to redirect to
		return viewURL;
		
	}
}
