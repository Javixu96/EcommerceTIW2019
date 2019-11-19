
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
		String searchQuery = 
				request.getParameter("searchQuery")!= null ? request.getParameter("searchQuery") : "all";
		String searchCategory = 
				request.getParameter("searchCategory") != null ? request.getParameter("searchCategory") : "1";
		
		System.out.println("SearchHandler con query: "+ searchQuery + " categoria: " + searchCategory);
				
			
		ProductManager productManager = new ProductManager();
		List<Product> products = null;
		
		if(searchQuery.equals("all") && searchCategory.equals("1")) {
			//pintar todos
			System.out.println("pintando todos");
			products = productManager.findAll();
			
		} else if (!searchQuery.equals("all") && searchCategory.equals("1")) {
			//pintar todos los de la query de cualquier categor�a
			System.out.println("pintando todos los de la query de cualquier categor�a");
			products = productManager.findBySimilarTitle(searchQuery);
			
		} else if (searchQuery.equals("all") && !searchCategory.equals("1")) {
			//pintar todos los de una misma categor�a
			System.out.println("pintando todos los de una misma categor�a");
			products = productManager.findByCategory(searchCategory);
			
		} else {
			//pintar los de la query de una categor�a
			System.out.println("pintando los de la query de una categor�a");
			products = productManager.findByNameAndCategory(searchQuery, searchCategory);
			
		}

	
		request.setAttribute("allProducts", products);
		for (Product product : products) {
			System.out.println("Hola" + product.getProductName());
		}
		request.setAttribute("searchQuery", searchQuery);
		
		return "shop.jsp";
		
	}

}
