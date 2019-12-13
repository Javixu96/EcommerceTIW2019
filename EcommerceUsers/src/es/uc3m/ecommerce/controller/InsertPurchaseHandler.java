package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.ConfirmationNumber;
import es.uc3m.ecommerce.model.Product;
import es.uc3m.ecommerce.model.Purchas;
import es.uc3m.ecommerce.model.PurchaseData;


/*
* Handler para añadir un producto nuevo
*/
public class InsertPurchaseHandler implements IHandler {
	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// Configuracion del cliente
		client = ClientBuilder.newClient();

		// Path al recurso. Se llama al microservicio Banco
		webTarget = client.target("http://localhost:13102");

		webTargetPath = webTarget.path("bank");

		// Request con tipo de dato
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
		
		// Parametros y total de la compra
		String cardNumber = request.getParameter("card");
		Integer expirationMonth = Integer.parseInt(request.getParameter("expiration_month"));
		Integer expirationYear = Integer.parseInt(request.getParameter("expiration_year"));
		String cvv = request.getParameter("cvv");
		Integer purchaseCost = (Integer) session.getAttribute("cartTotal");
	
		PurchaseData data = new PurchaseData();
		data.setCardNumber(cardNumber);
		data.setCvv(cvv);
		data.setExpMonth(expirationMonth);
		data.setExpYear(expirationYear);
		data.setPurchaseCost(purchaseCost);
		
		// Invocar al servicio
		resp= invocationBuilder.post(Entity.entity(data,MediaType.APPLICATION_JSON));

		// Codigo HTTP
		int status = resp.getStatus();
		int confirmationCode=8888;
		
		// Todo OK
		if (status == 200) {
			// Consumir el recurso. Se obtiene el codigo de confirmacion
			ConfirmationNumber code = (ConfirmationNumber)resp.readEntity(ConfirmationNumber.class);
			confirmationCode=code.getConfirmationNumber();	
		}
	  
		  Appuser u = (Appuser)session.getAttribute("user");
		  
		  @SuppressWarnings("unchecked")
		  List<Product> cartList = (List<Product>) session.getAttribute("cartList");
		  
		  @SuppressWarnings("unchecked")
		  List<Integer> cartQuantities = (List<Integer>)session.getAttribute("cartQuantities"); 
		  
		  // Configuracion del cliente ClientConfig config = new ClientConfig(); Client
		  
		  // Path al recurso WebTarget webtarget =
		  client.target("http://localhost:13101");
		  
		  webTargetPath =  webTarget
				  .path("purchases");
		  
		  Purchas purchase = new Purchas();
		  
		  for (int i = 0; i < cartList.size(); i++) { 
			  purchase.setAppuser(u);
			  // purchase.setConfirmationCode(confirmationCode); 
			  purchase.setConfirmationCode(22222); 
			  purchase.setProduct(cartList.get(i));
			  System.out.println("aaaaaaaaaaaaaaaaaaa"+cartList.get(i).getProductName());
			  purchase.setProductQuantity(cartQuantities.get(i));
			  System.out.println("bbbbbbbbbbbbbbbb"+(int)cartQuantities.get(i));
			  System.out.println("ccccccccccc" + u.getEmail());
			  invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
			  resp= invocationBuilder.post(Entity.entity(purchase,MediaType.APPLICATION_JSON));
			  
			  Purchas p=(Purchas)resp.readEntity(Purchas.class);
		  }
		 
		return "index.jsp";
	}

}
