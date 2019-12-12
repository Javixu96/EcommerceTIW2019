package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

import es.uc3m.ecommerce.model.*;

/*
* Handler que prerpara la lista de pedidos de un usuario comprador
*/
public class ShowAllPurchasesHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Configuracion del cliente
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		// Id del usuario (string)
		HttpSession session = request.getSession();
		Appuser user = (Appuser) session.getAttribute("user");
		String userId = String.valueOf(user.getUserId());
		
		// Path al recurso
		WebTarget webtarget = client.target("http://localhost:13101");
		
		WebTarget webTargetPath = webtarget
				.path("users")
				.path("buyers")
				.path(userId);
		
		// Request con tipo de dato
		Invocation.Builder invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
		
		// Invocar al servicio
		Response responsews = invocationBuilder.get();
		
		// Codigo HTTP
		int status = responsews.getStatus();
		
		// Consumir el recurso
		Integer[] integers = responsews.readEntity(Integer[].class);
		List<Integer> purchases = new ArrayList<Integer>(integers.length);
		for (int i : integers)
		{
		    purchases.add(i);
		}
		
		request.setAttribute("allPurchases", purchases);
		
		return "purchase_list.jsp";
	
	}
}
