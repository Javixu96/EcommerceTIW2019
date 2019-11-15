package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;

public class ShowProfileHandler implements IHandler {
	
	@Override 
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		System.out.println("SHOW PROFILE HANDLER");

		// Retrieve current user data by accession session attribute	
		HttpSession session = request.getSession();
		Appuser appuser = (Appuser) session.getAttribute("user");
		String viewURL = null; 
		
		if(session.getAttribute("user") == null) {
			
			System.out.println("No user logged in - SHOW PROFILE REQUEST HANDLER");
			
			viewURL = new ForbiddenPageHandler().handleRequest(request, response);
			
		} else {
						
			System.out.println("The values of the user when logged in before editing are: ");
			System.out.println(appuser.getEmail());
			System.out.println(appuser.getPw());
			System.out.println(appuser.getUserName());
			System.out.println(appuser.getUserSurnames());
			System.out.println(appuser.getPostalAddress());
			
			
			session.setAttribute("user", appuser);
			
			// Session attributes run on serverside, and cannot be accessed in the browser of the client
			// Therefore, we need to create a request attribute to access the user data in any JSP
			request.setAttribute("userName", appuser.getUserName());	
			request.setAttribute("userSurnames", appuser.getUserSurnames());
			request.setAttribute("userAddress", appuser.getPostalAddress());
			request.setAttribute("userEmail", appuser.getEmail());	
			request.setAttribute("userPw", appuser.getPw());
			
			viewURL = "profile.jsp";
			
		}
		
		return viewURL;
		
	}
	

}
