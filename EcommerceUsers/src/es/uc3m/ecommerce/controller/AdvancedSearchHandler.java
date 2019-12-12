package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uc3m.ecommerce.model.Product;

/*
 * Handler que se encarga de la busqueda avanzada. Dependiendo de la opcion que elija el usuario, redirige a un metodo u a otro
*/
public class AdvancedSearchHandler implements IHandler {
	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;	

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//recoger parametros query del formulario
		String advancedQuery = request.getParameter("advanced_query");
		String searchField = request.getParameter("search_field");
		//si uno de los parametros de precio no es introducido en base de datos, es guardado como String vacia
		String minPrice = request.getParameter("min_price");
		String maxPrice = request.getParameter("max_price");
	
		List<Product> products = new ArrayList<Product>();
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:13100");
		WebTarget webTargetPath;
		Response responsews;
		Product[] AllProducts;
		//filtrar por tipo de busqueda
		switch(searchField) {
			case "product_name":
				//Buscando por nombre
				webTargetPath = webTarget
					.path("products").queryParam("productName", advancedQuery)
					.queryParam("priceMin", minPrice)
					.queryParam("priceMax", maxPrice);
				invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
				responsews = invocationBuilder.get();
				
				AllProducts=responsews.readEntity(Product[].class);
				for(int i=0;i<AllProducts.length;i++) {
					products.add(AllProducts[i]);
				}
				break;
			case "product_description":
				//"Buscando por descripcion
				webTargetPath = webTarget
					.path("products").queryParam("shortDesc", advancedQuery)
					.queryParam("priceMin", minPrice)
					.queryParam("priceMax", maxPrice);
				invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
				responsews = invocationBuilder.get();
				
				AllProducts=responsews.readEntity(Product[].class);
				for(int i=0;i<AllProducts.length;i++) {
					products.add(AllProducts[i]);
				}
				break;
			case "product_category":
				//"Buscando por categoria
				webTargetPath = webTarget
					.path("products").queryParam("category", advancedQuery)
					.queryParam("priceMin", minPrice)
					.queryParam("priceMax", maxPrice);
				invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
				responsews = invocationBuilder.get();
				
				AllProducts=responsews.readEntity(Product[].class);
				for(int i=0;i<AllProducts.length;i++) {
					products.add(AllProducts[i]);
				}
				break;
			case "product_all":
				List<Product>resultado = new LinkedList<>();
				
				//"Buscando por todo
				webTargetPath = webTarget
					.path("products").queryParam("shortDesc", advancedQuery)
					.queryParam("priceMin", minPrice)
					.queryParam("priceMax", maxPrice);
				invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
				responsews = invocationBuilder.get();
				
				AllProducts=responsews.readEntity(Product[].class);
				for(int i=0;i<AllProducts.length;i++) {
					products.add(AllProducts[i]);
				}
				resultado.addAll(products);
				
				webTargetPath = webTarget
					.path("products").queryParam("productName", advancedQuery)
					.queryParam("priceMin", minPrice)
					.queryParam("priceMax", maxPrice);
				invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
				responsews = invocationBuilder.get();
					
				AllProducts=responsews.readEntity(Product[].class);
				for(int i=0;i<AllProducts.length;i++) {
					products.add(AllProducts[i]);
				}
				resultado.addAll(products);
				webTargetPath = webTarget
					.path("products").queryParam("productName", advancedQuery)
					.queryParam("priceMin", minPrice)
					.queryParam("priceMax", maxPrice);
				invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
				responsews = invocationBuilder.get();
						
				AllProducts=responsews.readEntity(Product[].class);
				for(int i=0;i<AllProducts.length;i++) {
					products.add(AllProducts[i]);
				}
				resultado.addAll(products);	
				break;
		}
		
		String newSearchQuery = ""; //limpiar la query para que en el buscador no pinte nada
		request.setAttribute("searchQuery", newSearchQuery);
		request.setAttribute("allProducts", products); //cargar la lista de productos que devuelve la busqueda
		
		return "shop.jsp";
	}

}
