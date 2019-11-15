package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ForbiddenPageHandler implements IHandler{

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("No user logged in. Access forbidden - FORBIDDEN PAGE HANDLER");

		// When no access to view granted, redirect to login.jsp
		String viewURL = "login.jsp"; 
		// Set request attribute to trigger JS warning in view login.jsp
		request.setAttribute("needsLoginError", 1);
		
		return viewURL;
	}

}
