
package es.uc3m.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.model.Product;


public class SearchHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		String searchQuery = 
				!request.getParameter("searchQuery").equals("") ? request.getParameter("searchQuery") : "all";
		String searchCategory = 
				request.getParameter("searchCategory") != null ? request.getParameter("searchCategory") : "1";
		
		ProductManager productManager = new ProductManager();
		List<Product> products = null;
		
		if(searchQuery.equals("all") && searchCategory.equals("1")) {
			//pintar todos
			products = productManager.findAll();
			
		} else if (!searchQuery.equals("all") && searchCategory.equals("1")) {
			//pintar todos los de la query de cualquier categoría
			products = productManager.findBySimilarTitle(searchQuery);
			
		} else if (searchQuery.equals("all") && !searchCategory.equals("1")) {
			//pintar todos los de una misma categoría
			products = productManager.findByCategory(searchCategory);
			
		} else {
			//pintar los de la query de una categoría			
			products = productManager.findByNameAndCategory(searchQuery, searchCategory);
			
		}
		
		String newSearchQuery = request.getParameter("searchQuery")!= null ? request.getParameter("searchQuery") : "";
		request.setAttribute("searchQuery", newSearchQuery);
		request.setAttribute("allProducts", products);
		
		return "shop.jsp";
	}
}
