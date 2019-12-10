package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import es.uc3m.ecommerce.manager.CategoryManager;
import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.*;

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
import es.uc3m.ecommerce.model.Category;

/*
* Handler que muestra un producto con sus atributos para que el vendedor pueda modificarlos
*/
public class ShowProductForModifyHandler implements IHandler {

	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;
	
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		client = ClientBuilder.newClient();
		//contador para saber cual es el producto que queremos modificar
		int counter=Integer.parseInt(request.getParameter("contadorModi"));
		
		HttpSession mySession = request.getSession(true);
		
		Product product = (Product)mySession.getAttribute("productToModify"+counter);
		
		request.setAttribute("product", product);
		request.setAttribute("parentCategory", findCategoryById(product.getCategoryBean().getParentId()));
	
		return "modif_product.jsp";
	}
	
	
	
	private Category findCategoryById(int categoryId) {
		String path = "categories/" + categoryId;
		webTarget = client.target("http://localhost:13100");		
		webTargetPath = webTarget.path(path);
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
		Category category = null;
		
		// Invocar al servicio
		resp = invocationBuilder.get();
		if (resp.getStatus() == 200) {
			category = resp.readEntity(Category.class);
		} 
		
		return category;
	}
}