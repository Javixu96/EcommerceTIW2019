package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import es.uc3m.ecommerce.manager.CategoryManager;
import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.model.Product;
import es.uc3m.ecommerce.model.Category;

public class ShowMsg1to1Handler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub				
		
		int counter=Integer.parseInt(request.getParameter("contadorMsg"));
		
		HttpSession session = request.getSession(true);
		
		Appuser user = (Appuser) session.getAttribute("sender"+counter);
		
		session.setAttribute("sender", user);
				
	
		return "readMessage.html";
	}
}