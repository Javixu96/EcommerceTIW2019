package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;
import es.uc3m.ecommerce.model.Purchas;

public class CartRequestHandler implements IHandler{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// The cart will contain products and will be a list put into a session attribute for the logged user
		// Session attribute = not persistent if log out
		
		String viewURL = null; 
		HttpSession session = request.getSession();
		Appuser u = (Appuser) session.getAttribute("user");
		
		if(session.getAttribute("user") == null) {
			
			System.out.println("No user logged in. Access forbidden - CART REQUEST HANDLER");
			viewURL = new ForbiddenPageHandler().handleRequest(request, response);
			
		} else {
			
			System.out.println("Session user: " + u.getEmail());
			System.out.println("Acces to cart granted - CART REQUEST HANDLER");
			
			// return the good content
			viewURL = "cart.jsp";
		
		}
					
		return viewURL;
	}

}
