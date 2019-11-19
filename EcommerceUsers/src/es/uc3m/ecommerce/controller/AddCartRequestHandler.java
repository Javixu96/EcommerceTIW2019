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
			
			List<Product> cartList = null;
			List<Integer> cartQuantities = null;

			
			if(session.getAttribute("cartList") == null) {
				cartList = new ArrayList<Product>();
				cartQuantities = new ArrayList<Integer>();

				session.setAttribute("cartTotal", 0);

			} else {
				cartList = (List<Product>) session.getAttribute("cartList");
				cartQuantities = (List<Integer>) session.getAttribute("cartQuantities");

			}
			
			// Add the new product to cartHash -> key = productId, value=quantity
			int productId = Integer.parseInt(request.getParameter("productId"));
			int q = Integer.parseInt(request.getParameter("quantity_input"));
			ProductManager pManager = new ProductManager();
			Product p = pManager.findById(productId);

			cartList.add(p);
			cartQuantities.add(q);
	
			session.setAttribute("cartList", cartList);
			session.setAttribute("cartQuantities", cartQuantities);
			
			int total = (int) session.getAttribute("cartTotal") + p.getPrice();
			session.setAttribute("cartTotal", total);
			for(Product item: cartList) {
				System.out.println("La lista carrito tiene este item: " + item.getProductName());
			}

			
			// STAY IN THE PRODUCT PAGE, BUT UPDATE HEADER AND SHOW A MESSAGE 
			// The ShowProductHandler will be triggered and try to load based on the productId 
			request.setAttribute("product", p);
			request.setAttribute("productId", p.getProductId());
			request.setAttribute("newProductAdded", 1);
			viewURL = "product.jsp";
		
		}
					
		return viewURL;
		
		
	}

}
