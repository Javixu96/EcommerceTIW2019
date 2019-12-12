
package es.uc3m.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

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
 * Handler que realiza la búsqueda simple y el filtrado por categorias
*/
public class SearchHandler implements IHandler {
	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		//recoge los parametros de la busqueda y filtrado. Si alguno de ellos no se pasa, se cambia al genérico
		String searchQuery = 
				!request.getParameter("searchQuery").equals("") ? request.getParameter("searchQuery") : "all";
		String searchCategory = 
				!request.getParameter("searchQuery").equals("") ? request.getParameter("searchCategory") : "1";
		

		List<Product> products = new ArrayList<Product>();
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:13100");
		
		if(searchQuery.equals("all") && searchCategory.equals("1")) {
			//pintar todos	
			WebTarget webTargetPath = webTarget
					.path("products");
			invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
			Response responsews = invocationBuilder.get();
			
			Product[] AllProducts=responsews.readEntity(Product[].class);
			for(int i=0;i<AllProducts.length;i++) {
				products.add(AllProducts[i]);
			}
			
		} else if (!searchQuery.equals("all") && searchCategory.equals("1")) {
			//pintar todos los de la query de cualquier categoría
			WebTarget webTargetPath = webTarget
					.path("products").queryParam("productName",searchQuery);
			invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
			Response responsews = invocationBuilder.get();
			
			Product[] AllProducts=responsews.readEntity(Product[].class);
			for(int i=0;i<AllProducts.length;i++) {
				products.add(AllProducts[i]);
			}
			
		} else if (searchQuery.equals("all") && !searchCategory.equals("1")) {
			//pintar todos los de una misma categoría
			WebTarget webTargetPath = webTarget
					.path("products").queryParam("category",searchCategory);
			invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
			Response responsews = invocationBuilder.get();
			
			Product[] AllProducts=responsews.readEntity(Product[].class);
			for(int i=0;i<AllProducts.length;i++) {
				products.add(AllProducts[i]);
			}
			
		} else {
			//pintar los de la query de una categoría			
			WebTarget webTargetPath = webTarget
					.path("products").queryParam("category",searchCategory).queryParam("productName", searchQuery);
			invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
			Response responsews = invocationBuilder.get();
			
			Product[] AllProducts=responsews.readEntity(Product[].class);
			for(int i=0;i<AllProducts.length;i++) {
				products.add(AllProducts[i]);
			}
			
		}
		//preparamos la query para mostrarse de vuelta en el buscador
		String newSearchQuery = request.getParameter("searchQuery")!= null ? request.getParameter("searchQuery") : "";
		request.setAttribute("searchQuery", newSearchQuery);
		request.setAttribute("allProducts", products);
		
		return "shop.jsp";
	}
}
