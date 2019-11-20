package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import es.uc3m.ecommerce.manager.*;
import es.uc3m.ecommerce.model.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.model.Product;

/*
* Handler que gestiona la vista detalle de un producto determinado
*/
public class ShowProductHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String viewURL = null;
		//se obtiene el producto con el manager pasandole el id 
		int productId = Integer.parseInt(request.getParameter("productId"));
		ProductManager productManager = new ProductManager();
		Product product = productManager.findById(productId);
		
		request.setAttribute("product", product);
	
		viewURL = "product.jsp";
		
		return viewURL;
	}
}

