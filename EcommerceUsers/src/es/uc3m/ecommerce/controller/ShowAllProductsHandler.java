package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uc3m.ecommerce.model.*;

/*
 * Handler que gestiona la vista de todos los productos en la tienda
*/
public class ShowAllProductsHandler implements IHandler {
	
	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;	

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:13100");
		String path = "products";
		webTargetPath = webTarget.path(path);
		
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
		resp= invocationBuilder.get();
		
		Product[] allProduct = resp.readEntity(Product[].class);
		List<Product> products = new ArrayList<Product>();
		
		for(int i=0;i<allProduct.length;i++) {
			products.add(allProduct[i]);
			System.out.println(allProduct[i].getProductName());
		}
		//se obtienen los productos con el manager

		
		request.setAttribute("allProducts", products);
		
		return "shop.jsp";
	}
}
