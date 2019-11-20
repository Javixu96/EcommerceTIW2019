package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.ecommerce.manager.*;
import es.uc3m.ecommerce.model.*;

public class ShowAllProductsHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		ProductManager productManager = new ProductManager();
		//se obtiene los productos con el manager
		List<Product> products = productManager.findAll();
		
		request.setAttribute("allProducts", products);
		
		return "shop.jsp";
	}
}
