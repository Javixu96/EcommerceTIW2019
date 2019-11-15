package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WishlistRequestHandler implements IHandler{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String viewURL;

		if(session.getAttribute("user") == null) {
			System.out.println("No user logged in - WISHLIST REQUEST HANDLER");
			viewURL = new ForbiddenPageHandler().handleRequest(request, response);
		} else {
			// return the good content
			viewURL = "wishlist.jsp";
		}
		
		return viewURL;
	}

}
