package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.ecommerce.manager.*;
import es.uc3m.ecommerce.model.*;

/*
* Handler que muestra los productos de un pedido determinado al usuario
*/
public class ShowPurchaseHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//se muestran los productos agrupados por pedido (mismo confirmation code)
		PurchaseManager purchaseManager = new PurchaseManager();
		Integer code = Integer.parseInt(request.getParameter("confirmationCode"));
		//se obtiene los pedidos con el mismo confirmationCode
		List<Purchas> purchases = purchaseManager.findPurchase(code);

		List<Product> products = new ArrayList<Product>();
		
		//se almacena todos los productos como un objeto
		for (int i=0; i<purchases.size(); i++) {
			Product p = purchases.get(i).getProduct();
			products.add(p);
		}
	
		request.setAttribute("productPurchased", products);
		request.setAttribute("purchase", purchases);
		
		return "purchase.jsp";
	}
}
