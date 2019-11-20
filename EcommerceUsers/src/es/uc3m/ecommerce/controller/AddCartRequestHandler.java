package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;

public class AddCartRequestHandler implements IHandler{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String viewURL = null; 
		HttpSession session = request.getSession();
		Appuser u = (Appuser) session.getAttribute("user");
		
		if(session.getAttribute("user") == null) {
			
			System.out.println("No user logged in. Access forbidden - ADD TO CART REQUEST HANDLER");
			viewURL = new ForbiddenPageHandler().handleRequest(request, response);
			
		} else {
			
			System.out.println("Session user: " + u.getEmail());
			System.out.println("Addition to cart granted - ADD TO CART REQUEST HANDLER");
			System.out.println("action parameter" + request.getParameter("action"));
			System.out.println("product parameter" + request.getParameter("productId"));
			System.out.println("quantity_input parameter" + request.getParameter("quantity_input"));


			int action = Integer.parseInt(request.getParameter("action"));
			String productId = request.getParameter("productId");
			System.out.println("Action parameter: " + action);
			System.out.println("ProductId parameter: " + productId);
			
			List<Product> cartList;
			List<Integer> cartQuantities;
			int cartTotal = 0; 

			// Si el usuario todavía no ha usado el carro, creamos su atributo de sesión con los productos y cantidades del carro vacíos
			if(session.getAttribute("cartList") == null) {
				cartList = new ArrayList<Product>();
				cartQuantities = new ArrayList<Integer>();
				session.setAttribute("cartTotal", 0);

			// Si el usuario ya ha usado el carro, los atributos de sesión ya existen
			} else {
				cartList = (List<Product>) session.getAttribute("cartList");
				cartQuantities = (List<Integer>) session.getAttribute("cartQuantities");
				cartTotal = (int) session.getAttribute("cartTotal");
			}
			
			// ACTION = 1 - ELIMINAR PRODUCTO DEL CARRO
			if(action == 1) {
				
				System.out.println("ACTION = REMOVE");
				// Tomamos el ID del producto a eliminar de los parametros enviados con la request
				int id = Integer.parseInt(productId);
				// Buscamos el producto a eliminar por productId
				for(int i = 0; i < cartList.size(); i++) {
					if(cartList.get(i).getProductId() == id) {
						// Restar el subtotal del producto por la cantidad eliminados del carro
						cartTotal = cartTotal - (cartList.get(i).getPrice() * cartQuantities.get(i));
						// Eliminar producto del carro
						cartList.remove(i);
						// Eliminar cantidad correspondiente al producto eliminado
						cartQuantities.remove(i);
						break;
					}
				}
				
				// Retornamos la vista de cart actualizada 
				viewURL = "cart.jsp";
				

			// ACTION = 2 - AÑADIR PRODUCTO AL CARRO	
			} else if(action == 2) {
				
				System.out.println("ACTION = ADD");
				// Tomamos el ID del producto a añadir de los parametros enviados con la request
				int id = Integer.parseInt(productId);
				ProductManager pManager = new ProductManager();
				Product p = pManager.findById(id);

				// Añadimos el nuevo producto a la lista cartList
				cartList.add(p);

				// Generamos atributos de request para pintar la vista de este mismo producto con un mensaje 
				request.setAttribute("product", p);
				request.setAttribute("productId", p.getProductId());
				request.setAttribute("newProductAdded", 1);
				
				// Añadimos la cantidad deseada a la lista cartQuantities (en la misma posición que su producto al que se refiere)
				int q = Integer.parseInt(request.getParameter("quantity_input"));
				//int q = (int) request.getAttribute("quantity_input");
				cartQuantities.add(q);
				
				// Sumamos el nuevo producto a la cuenta global de productos en cart
				cartTotal = cartTotal + (q * p.getPrice());
				// Retornamos la vista de producto de nuevo pero se actualizará el header con los nuevos atributos de sesión
				viewURL = "product.jsp";
				
				for(int i = 0; i < cartList.size(); i ++) {
					System.out.println("Producto en cartList: " + cartList.get(i).getProductName());
					System.out.print("en cantidad: " + cartQuantities.get(i));

				}
				
			// ACTION = 0 - ENSEÑAR CARRITO
			} else if(action == 0) {
				
				System.out.println("ACTION = JUST SHOW");
				// Mostrar el carrito actual
				viewURL = "cart.jsp";

				
			// ACTION = 3 - UPDATE QUANTITY	
			} else if(action == 3) {
				
				System.out.println("ACTION = UPDATE QUANTITY");
				
				int operation = Integer.parseInt(request.getParameter("operation"));
				int p = Integer.parseInt(productId);
				int q = 0;  
				int index = -1;
				
				ProductManager pManager = new ProductManager();
				Product product = pManager.findById(p);
				
				for(int i = 0; i < cartList.size(); i ++) {
					if(cartList.get(i).getProductId() == p) {
						q = cartQuantities.get(i);
						index = i;
					}
				}
				
				// operation = 0 -> restar cantidad en 1 unidad
				if(operation == 0) {
					q--;
					cartTotal = cartTotal - product.getPrice();
				// operation = 1 -> sumar cantidad en 1 unidad
				} else if(operation == 1) {
					q++;
					cartTotal = cartTotal + product.getPrice();
				// Error en parámetro operation
				} else {
					System.out.println("Error de parámetro operation en action 3  -  edit_cart.html");
				}
				
				cartQuantities.set(index, q);
				
				// Mostrar el carrito actualizado
				viewURL = "cart.jsp";
				
				for(int i = 0; i < cartList.size(); i ++) {
					System.out.println("Producto en cart para checout: " + cartList.get(i).getProductName());
					System.out.print("en cantidad: " + cartQuantities.get(i));
				}
				

			
			} else {
				System.out.println("ERROR ELSE");
			}
			
			session.setAttribute("cartList", cartList);
			session.setAttribute("cartQuantities", cartQuantities);
			session.setAttribute("cartTotal", cartTotal);
					
		}
					
		return viewURL;
		
		
	}

}
