package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;

public class WishlistRequestHandler implements IHandler{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("ADD WISHLIST HANDLER");
		
		String viewURL = null; 
		HttpSession session = request.getSession();
		Appuser u = (Appuser) session.getAttribute("user");

		if(session.getAttribute("user") == null) {
			
			System.out.println("No user logged in. Access forbidden - ADD TO WISHLIST REQUEST HANDLER");
			viewURL = new ForbiddenPageHandler().handleRequest(request, response);
			
		} else {
			
			System.out.println("Addition to cart granted - ADD TO WISHLIST REQUEST HANDLER");
			int action = Integer.parseInt(request.getParameter("action"));
			String productId = request.getParameter("productId");
			System.out.println("Action parameter: " + action);
			System.out.println("ProductId parameter: " + productId);
			
			List<Product> wishlistList;
			int wishlistTotal; 

			// Si el usuario todavía no ha usado el carro, creamos su atributo de sesión con los productos y cantidades del carro vacíos
			if(session.getAttribute("wishlistList") == null) {
				wishlistList = new ArrayList<Product>();
				wishlistTotal = 0; 

			// Si el usuario ya ha usado el carro, los atributos de sesión ya existen
			} else {
				wishlistList = (List<Product>) session.getAttribute("wishlistList");
				wishlistTotal = (int) session.getAttribute("wishlistTotal");
			}
			// ACTION = 0 - MOSTRAR WISHLIST
			if(action == 0) {
				
				System.out.println("ACTION = JUST SHOW");
				// Mostrar simplemente la wishlist actual
				viewURL = "wishlist.jsp";
				
			//ACTION = 1 - QUITAR PRODUCTO DE WISHLIST
			} else if(action == 1) {
				
				System.out.println("ACTION = REMOVE");
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
				
				for(Product runner: wishlistList) {
					System.out.println("Producto en wishlist: " + runner.getProductName());
				}
				
			// ACTION = 2 - AÑADIR PRODUCTO A WISHLIST	
			} else if(action == 2) {
				
				System.out.println("ACTION = ADD");
				// Tomamos el nuevo producto 
				int id = Integer.parseInt(productId);
				ProductManager pManager = new ProductManager();
				Product p = pManager.findById(id);

				// Añadimos el nuevo producto a la lista wishlistList
				wishlistList.add(p);
				request.setAttribute("product", p);
				request.setAttribute("productId", p.getProductId());
				request.setAttribute("newProductAdded", 1);
				
				// Sumamos el nuevo producto a la cuenta global de productos en wishlist
				wishlistTotal ++;
				// Retornamos la vista de producto de nuevo pero se actualizará el header con los nuevos atributos de sesión
				viewURL = "product.jsp";
				
				for(Product runner: wishlistList) {
					System.out.println("Producto en wishlist: " + runner.getProductName());
				}
				
			} else {
				System.out.println("ERROR ELSE");
			}
			// Actualizar session attributes con los valores modificados
			session.setAttribute("wishlistList", wishlistList);			
			session.setAttribute("wishlistTotal", wishlistTotal);
		}
					
		return viewURL;
		
	}

}
