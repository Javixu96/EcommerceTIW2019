package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;

/*
* Handler que prepara la lista de productos a la venta de un determinado vendedor
*/
public class ShowMyProductListHandler implements IHandler {
	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;
	
	@Override 
	public String handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,IOException {
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:13101");
		
		HttpSession session = request.getSession();
		Appuser appuser = (Appuser) session.getAttribute("user");
		List<Product> resultado=new ArrayList<Product>();

		WebTarget webTargetPath = webTarget
				.path("users")
				.path(Integer.toString(appuser.getUserId()))
				.path("products");
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
		Response responsews = invocationBuilder.get();
		
		Product[] allMyProducts=responsews.readEntity(Product[].class);
		for(int i=0;i<allMyProducts.length;i++) {
			resultado.add(allMyProducts[i]);
		}
			
		request.setAttribute("allProducts", resultado);		
 
		return "product_list_seller.jsp";
}
	

}
