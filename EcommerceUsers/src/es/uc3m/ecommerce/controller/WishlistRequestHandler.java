package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;

/*
 * Handler que gestiona la wishlist: mostrar, añadir y borrar elementos
*/
public class WishlistRequestHandler implements IHandler{
	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;

	@SuppressWarnings("unchecked")
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String viewURL = null; 
		HttpSession session = request.getSession();

		if(session.getAttribute("user") == null) {
			//si se esta intentado acceder a una pagina protegida por login, redirigir al handler correspondiente
			viewURL = new ForbiddenPageHandler().handleRequest(request, response);
		} else {
			
			int action = Integer.parseInt(request.getParameter("action"));
			String productId = request.getParameter("productId");
			List<Product> wishlistList;
			int wishlistTotal; 

			// Si el usuario todavï¿½a no ha usado el carro, creamos su atributo de sesiï¿½n con los productos y cantidades del carro vacï¿½os
			if(session.getAttribute("wishlistList") == null) {
				wishlistList = new ArrayList<Product>();
				wishlistTotal = 0; 

			// Si el usuario ya ha usado el carro, los atributos de sesiï¿½n ya existen
			} else {
				wishlistList = (List<Product>) session.getAttribute("wishlistList");
				wishlistTotal = (int) session.getAttribute("wishlistTotal");
			}
			// ACTION = 0 - MOSTRAR WISHLIST
			if(action == 0) {
				
				// Mostrar simplemente la wishlist actual
				viewURL = "wishlist.jsp";
				
			//ACTION = 1 - QUITAR PRODUCTO DE WISHLIST
			} else if(action == 1) {
				
				int id = Integer.parseInt(productId);
				// Buscamos el producto a eliminar por productId
				for(int i = 0; i < wishlistList.size(); i++) {
					if(wishlistList.get(i).getProductId() == id) {
						wishlistList.remove(i);
						break;
					}
				}
				
				// Restamos el producto eliminado a la cuenta global de productos en wishlist
				wishlistTotal --;
				// Retornamos l a vista de wishlist actualizada 
				viewURL = "wishlist.jsp";
				
				
			// ACTION = 2 - Aï¿½ADIR PRODUCTO A WISHLIST	
			} else if(action == 2) {
				
				// Tomamos el nuevo producto 

				
				client = ClientBuilder.newClient();
				webTarget = client.target("http://localhost:13100");		
				WebTarget webTargetPath = webTarget
						.path("products")
						.path(productId);
				invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
				Response responsews = invocationBuilder.get();

				// Aï¿½adimos el nuevo producto a la lista wishlistList
				Product p=(Product)responsews.readEntity(Product.class);
				wishlistList.add(p);
				request.setAttribute("product", p);
				request.setAttribute("productId", p.getProductId());
				request.setAttribute("newProductAdded", 1);
				
				// Sumamos el nuevo producto a la cuenta global de productos en wishlist
				wishlistTotal ++;
				// Retornamos la vista de producto de nuevo pero se actualizar el header con los nuevos atributos de sesiï¿½n
				viewURL = "product.jsp";
				
				
			} 
			
			// Actualizar session attributes con los valores modificados
			session.setAttribute("wishlistList", wishlistList);			
			session.setAttribute("wishlistTotal", wishlistTotal);
		}
					
		return viewURL;
		
	}

}
