package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

import org.glassfish.jersey.client.ClientConfig;

import es.uc3m.ecommerce.manager.*;
import es.uc3m.ecommerce.model.*;

/*
* Handler que muestra los productos de un pedido determinado al usuario
*/
public class ShowPurchaseHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ShowPurchaseHandler()");
		/*
		//se muestran los productos agrupados por pedido (mismo confirmation code)
		PurchaseManager purchaseManager = new PurchaseManager();
		Integer code = Integer.parseInt(request.getParameter("confirmationCode"));
		//se obtiene los pedidos con el mismo confirmationCode
		List<Purchas> purchases = purchaseManager.findPurchase(code);

		List<Product> products = new ArrayList<Product>();
		
		//se almacena todos los productos como un objeto
		for (int i=0; i<purchases.size(); i++) {
			Product p = purchases.get(i).getProduct();
			products.add(p);
		}
	
		request.setAttribute("productPurchased", products);
		request.setAttribute("purchase", purchases);
		
		return "purchase.jsp";
		*/
		
		// Configuracion del cliente
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
				
		// Id del usuario (string)
		String code = request.getParameter("confirmationCode");
				
		// Path al recurso
		WebTarget webtarget = client.target("http://localhost:13101");
				
		WebTarget webTargetPath = webtarget
			.path("users")
			.path("purchases")
			.path(code);
				
		// Request con tipo de dato
		Invocation.Builder invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
			
		// Invocar al servicio
		Response responsews = invocationBuilder.get();
				
		// Codigo HTTP
		int status = responsews.getStatus();
				
		// Consumir el recurso
		Purchas[] integers = responsews.readEntity(Purchas[].class);
		// List<Purchas> purchases = new ArrayList<Purchas>();
		List<Purchas> purchases = Arrays.asList(integers);
		
		//for (Purchas i : integers) {
			//purchases.add(i);
		//}
		
		List<Product> products = new ArrayList<Product>();
		
		//se almacena todos los productos como un objeto
		
		for (int i=0; i<purchases.size(); i++) {
			// purchases.add(integers[i]);
			Product p = purchases.get(i).getProduct();
			System.out.println("AAAAAMMMM"+p.getProductName());
			System.out.println(purchases.get(i).getProductQuantity());
			products.add(p);
		}
		
		request.setAttribute("productPurchased", products);
		request.setAttribute("purchase", purchases);
		
		return "purchase.jsp";
		
	}
}
