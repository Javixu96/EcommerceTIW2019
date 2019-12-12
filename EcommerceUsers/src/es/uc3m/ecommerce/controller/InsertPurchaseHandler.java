package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;
import es.uc3m.ecommerce.model.Purchas;


/*
* Handler para añadir un producto nuevo
*/
public class InsertPurchaseHandler implements IHandler {
	/*
	 * Convertir los productos a purchase Obtener codigo de confirmacion y llamar a
	 * la url para insertar pedido
	 * 
	 */
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// Configuracion del cliente
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);

		// Path al recurso. Se llama al microservicio Banco
		WebTarget webtarget = client.target("http://localhost:13102");

		WebTarget webTargetPath = webtarget.path("bank");

		// Request con tipo de dato
		Invocation.Builder invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
		
		// Parametros y total de la compra
		String cardNumber = request.getParameter("card");
		Integer expirationMonth = Integer.parseInt(request.getParameter("expiration_month"));
		Integer expirationYear = Integer.parseInt(request.getParameter("expiration_year"));
		String cvv = request.getParameter("cvv");
		Integer purchaseCost = (Integer) session.getAttribute("cartTotal");
		
		// Informacion del request body
		JsonObject requestBody = Json.createObjectBuilder()
				.add("card_number", cardNumber)
				.add("expiration_month", expirationMonth)
				.add("expiration_year", expirationYear)
				.add("CVV", cvv)
				.add("purchaseCost", purchaseCost)
				.build();

		// Invocar al servicio
		Response responsews = invocationBuilder.post(Entity.json(requestBody));

		// Codigo HTTP
		int status = responsews.getStatus();
		Integer confirmationCode=8888;
		
		// Todo OK
		if (status == 200) {
			// Consumir el recurso. Se obtiene el codigo de confirmacion
			confirmationCode = responsews.readEntity(Integer.class);
			System.out.println("*** InsertPurchaseHandler() \n" + confirmationCode);		
		} else { // Error
			
		}
	  
		  Appuser u = (Appuser)session.getAttribute("user");
		  
		  @SuppressWarnings("unchecked")
		  List<Product> cartList = (List<Product>) session.getAttribute("cartList");
		  
		  @SuppressWarnings("unchecked")
		  List<Integer> cartQuantities = (List<Integer>)session.getAttribute("cartQuantities"); 
		  int cartTotal = (int)session.getAttribute("cartTotal");
		  
		  // Configuracion del cliente ClientConfig config = new ClientConfig(); Client
		  client = ClientBuilder.newClient(config);
		  
		  // Path al recurso WebTarget webtarget =
		  client.target("http://localhost:13101");
		  
		  webTargetPath =  webtarget
				  .path("users")
				  .path("purchases");
		  
		  // Request con tipo de dato Invocation.Builder invocationBuilder =
		  webTargetPath.request(MediaType.APPLICATION_JSON);
		  
		  Purchas purchase = new Purchas();
		  
		  for (int i = 0; i < cartList.size(); i++) { purchase.setAppuser(u);
		  purchase.setConfirmationCode(confirmationCode); purchase.setProduct(cartList.get(i));
		  purchase.setProductQuantity(cartQuantities.get(i));
		  purchase.setPurchaseId(22); 
		  invocationBuilder.post(Entity.entity(purchase, MediaType.APPLICATION_JSON)); }
		 
		return "index.jsp";
	}

}
