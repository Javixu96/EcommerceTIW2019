package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.model.Product;

public class AdvancedSearchHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String advancedQuery = request.getParameter("advanced_query");
		String searchField = request.getParameter("search_field");
		//si uno de los parametros de precio no es introducido en base de datos, es guardado como String vacia
		String minPrice = request.getParameter("min_price");
		String maxPrice = request.getParameter("max_price");
	
		ProductManager productManager = new ProductManager();
		List<Product> products = null;
		
		switch(searchField) {
			case "product_name":
				System.out.println("Buscando por nombre");
				products = productManager.findAllByNameFilterPrice(advancedQuery, minPrice, maxPrice);
				break;
			case "product_description":
				System.out.println("Buscando por descripcion");
				products = productManager.findAllByDescriptionFilterPrice(advancedQuery, minPrice, maxPrice);
				break;
			case "product_category":
				System.out.println("Buscando por categoria");
				products = productManager.findAllByCategoryFilterPrice(advancedQuery, minPrice, maxPrice);
				break;
			case "product_all":
				System.out.println("Buscando por todo");
				products = productManager.findAllMergeFilterPrice(advancedQuery, minPrice, maxPrice);
				break;
		}
		
		String newSearchQuery = "";
		request.setAttribute("searchQuery", newSearchQuery);
		request.setAttribute("allProducts", products);
		
		return "shop.jsp";
	}

}
