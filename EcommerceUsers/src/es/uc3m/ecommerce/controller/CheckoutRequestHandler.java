package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.model.Product;

public class CheckoutRequestHandler implements IHandler{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductManager im = new ProductManager();
		
		
		HttpSession session = request.getSession();
		List<Product> cartList = (List) session.getAttribute("cartList");
		List<Integer> cartQuantities = (List) session.getAttribute("cartQuantities");
		int total = (int)session.getAttribute("cartTotal");
		System.out.println(total);
		
		for(int i = 0; i < cartList.size(); i ++) {
			System.out.println("Item in checkout: " + cartList.get(i).getProductName()+" "+cartQuantities.get(i));
			// Take the new quantities edited from cart.jsp and update session attributes for checkout
			//cartQuantities.set(i, Integer.parseInt(request.getParameter("quantity_input_" + i)));
			cartList.get(i).setStock(cartList.get(i).getStock()-cartQuantities.get(i));
			
			try {
				im.modifyProduct(cartList.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		cartList.clear();
		cartQuantities.clear();
		
		session.setAttribute("cartList", cartList);
		session.setAttribute("cartQuantities", cartQuantities);
		
		return "sendOrderMessage.html";
	}

}
