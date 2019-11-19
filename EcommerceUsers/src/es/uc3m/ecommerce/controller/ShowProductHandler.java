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

public class ShowProductHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("SHOW PRODUCT HANDLER");
		String viewURL = null;
		// HttpSession session = request.getSession();

		int productId = Integer.parseInt(request.getParameter("productId"));
		ProductManager productManager = new ProductManager();
		Product product = productManager.findById(productId);
		
		System.out.println("El producto que se quiere ver es: " + product.getProductName());
		
		request.setAttribute("product", product);
		request.setAttribute("productId", product.getProductId());

		viewURL = "product.jsp";
		
		return viewURL;
	}
}

