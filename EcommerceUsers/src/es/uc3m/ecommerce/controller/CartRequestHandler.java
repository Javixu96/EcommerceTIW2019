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

public class CartRequestHandler implements IHandler{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String viewURL = null; 
		HttpSession session = request.getSession();
		Appuser u = (Appuser) session.getAttribute("user");
		
		if(session.getAttribute("user") == null) {
			viewURL = new ForbiddenPageHandler().handleRequest(request, response);
		} else {
			
			// Segun el codigo de accion recibido, haremos una cosa u otra
			int action = Integer.parseInt(request.getParameter("action"));
			String productId = request.getParameter("productId");
			
			// Inicializamos las variables que van a recibir los atributos de sesión
			List<Product> cartList;
			List<Integer> cartQuantities;
			int cartTotal = 0; 

			// Si el usuario todavia no ha usado el carro, creamos su atributo de sesi�n con los productos y cantidades del carro vac�os
			if(session.getAttribute("cartList") == null) {
				cartList = new ArrayList<Product>();
				cartQuantities = new ArrayList<Integer>();
				session.setAttribute("cartTotal", 0);

			// Si el usuario ya ha usado el carro, los atributos de sesion ya existen
			} else {
				cartList = (List<Product>) session.getAttribute("cartList");
				cartQuantities = (List<Integer>) session.getAttribute("cartQuantities");
				cartTotal = (int) session.getAttribute("cartTotal");
			}
			
			// ACTION = 1 - ELIMINAR PRODUCTO DEL CARRO
			if(action == 1) {
				
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
				

			// ACTION = 2 - A�ADIR PRODUCTO AL CARRO	
			} else if(action == 2) {
				
				// Tomamos el ID del producto a a�adir de los parametros enviados con la request
				int id = Integer.parseInt(productId);
				ProductManager pManager = new ProductManager();
				Product p = pManager.findById(id);

				// A�adimos el nuevo producto a la lista cartList
				cartList.add(p);

				// Generamos atributos de request para pintar la vista de este mismo producto con un mensaje 
				request.setAttribute("product", p);
				request.setAttribute("productId", p.getProductId());
				request.setAttribute("newProductAdded", 1);
				
				// A�adimos la cantidad deseada a la lista cartQuantities (en la misma posici�n que su producto al que se refiere)
				int q = Integer.parseInt(request.getParameter("quantity_input"));
				//int q = (int) request.getAttribute("quantity_input");
				cartQuantities.add(q);
				
				// Sumamos el nuevo producto a la cuenta global de productos en cart
				cartTotal = cartTotal + (q * p.getPrice());
				// Retornamos la vista de producto de nuevo pero se actualizar� el header con los nuevos atributos de sesi�n
				viewURL = "product.jsp";
				
				
				
			// ACTION = 0 - ENSE�AR CARRITO
			} else if(action == 0) {
				
				// Mostrar el carrito actual
				viewURL = "cart.jsp";

				
			// ACTION = 3 - MODIFICAR CANTIDAD
			} else if(action == 3) {
				
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
				// Error en par�metro operation
				} else {
					System.out.println("Error de parametro operation en action 3  -  edit_cart.html");
				}
				
				cartQuantities.set(index, q);
				
				// Mostrar el carrito actualizado
				viewURL = "cart.jsp";
				

			
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
