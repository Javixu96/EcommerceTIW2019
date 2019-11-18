package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.model.Product;

public class CheckoutRequestHandler implements IHandler{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<Product> cartList = (List) session.getAttribute("cartList");
		List<Integer> cartQuantities = (List) session.getAttribute("cartQuantities");
		
		for(int i = 0; i < cartList.size(); i ++) {
			// Take the new quantities edited from cart.jsp and update session attributes for checkout
			cartQuantities.set(i, Integer.parseInt(request.getParameter("quantity_input_" + i)));
		}
		
		return null;
	}

}
