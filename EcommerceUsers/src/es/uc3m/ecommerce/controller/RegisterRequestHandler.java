package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterRequestHandler implements RequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext appContext) throws ServletException, IOException {
		
		Object introducedNameObject = request.getParameter("name");
		Object introducedSurnameObject = request.getParameter("surname");
		Object introducedPhoneObject = request.getParameter("phone");
		Object introducedAddressObject = request.getParameter("address");
		Object introducedEmailObject = request.getParameter("email");
		Object introducedPasswordObject = request.getParameter("password");
		
		String viewURL = null;
		
		String introducedName = (String) introducedNameObject;
		String introducedSurname = (String) introducedSurnameObject;
		String introducedPhone = (String) introducedPhoneObject;
		String introducedAddress = (String) introducedAddressObject;
		String introducedEmail = (String) introducedEmailObject;
		String introducedPassword = (String) introducedPasswordObject;
		

		DataStore bd = (DataStore) appContext.getAttribute("bd");
		if(bd.getInfo(introducedEmail) != null) {
			// Email already registered
			viewURL = "alreadyRegistered.jsp";
		}
		else {
			UserDataModelBean newUserInfo = new UserDataModelBean(introducedName, introducedSurname, introducedPhone, introducedAddress, introducedEmail, introducedPassword);
			bd.saveInfo(newUserInfo);
			viewURL = "nowRegistered.jsp";
		}
	
		return viewURL;
	}

}
