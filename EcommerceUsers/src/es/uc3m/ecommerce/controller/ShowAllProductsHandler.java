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
		System.out.println("ShowAllProductsHandler");
		// TODO Auto-generated method stub	
		ProductManager productManager = new ProductManager();
		List<Product> products = productManager.findAll();
		// System.out.println("hola"+products.getProductName());
		
		CategoryManager categoryManager = new CategoryManager();
		List<List<Category>> categoryTree = categoryManager.findCategoryTree();
		
		request.setAttribute("allProducts", products);
		request.setAttribute("categoryTree", categoryTree);
		for (Product product : products) {
			System.out.println("Hola" + product.getProductName());
		}
		
		return "shop.jsp";
	}
}
