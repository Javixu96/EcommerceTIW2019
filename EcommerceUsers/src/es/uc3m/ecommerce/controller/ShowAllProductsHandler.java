package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.Product;

public class ShowAllProductsHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		ProductManager productManager = new ProductManager();
		List<Product> products = productManager.findAll();
		request.setAttribute("allProducts", products);
		*/
		
		
		UserManager userManager = new UserManager();
		List<Product> products = userManager.getAllProducts();
		// System.out.println("hola"+products.getProductName());
		request.setAttribute("allProducts", products);
		for (Product product : products) {
			System.out.println("Hola" + product.getProductName());
		}
		
		return "shop.jsp";
		// return "insertProduct.jsp";
	}
}
