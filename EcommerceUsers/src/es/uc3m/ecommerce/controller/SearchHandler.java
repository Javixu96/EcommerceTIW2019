/*
package es.uc3m.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.model.Product;


public class SearchHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//shopSearch.html
		String searchQuery = request.getParameter("searchQuery")!= null ? request.getParameter("searchQuery") : "";
		String searchCategory = request.getParameter("searchCategory") != null ? request.getParameter("searchCategory") : "1";
		
		System.out.println("SearchHandler con: 1 "+ searchQuery + " 2 " + searchCategory);
				
			
		ProductManager productManager = new ProductManager();
		List<Product> products = productManager.findBySimilarTitle(searchQuery, searchCategory);
	
		request.setAttribute("allProducts", products);
		for (Product product : products) {
			System.out.println("Hola" + product.getProductName());
		}
		
		return "shop.jsp";
		
	}

}
*/