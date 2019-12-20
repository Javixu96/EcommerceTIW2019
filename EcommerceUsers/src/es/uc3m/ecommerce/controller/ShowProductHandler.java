package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import es.uc3m.ecommerce.model.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uc3m.ecommerce.model.Product;

/*
* Handler que gestiona la vista detalle de un producto determinado
*/
public class ShowProductHandler implements IHandler {
	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;
	

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		client = ClientBuilder.newClient();
		
		String viewURL = null;
		//se obtiene el producto con el manager pasandole el id 
		int productId = Integer.parseInt(request.getParameter("productId"));
		String path = "products/" + productId;
		webTarget = client.target("http://localhost:13100");		
		webTargetPath = webTarget.path(path);
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
		resp = invocationBuilder.get();
		
		
		request.setAttribute("product", (Product)resp.readEntity(Product.class));
	
		viewURL = "product.jsp";
		
		return viewURL;
	}
}

