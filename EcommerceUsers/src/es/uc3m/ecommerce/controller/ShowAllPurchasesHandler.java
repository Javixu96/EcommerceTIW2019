package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.manager.*;
import es.uc3m.ecommerce.model.*;

public class ShowAllPurchasesHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		Appuser user = (Appuser) session.getAttribute("user");
		
		PurchaseManager purchaseManager = new PurchaseManager();
		List<Integer> purchases = purchaseManager.findAllConfirmationCode(user);
	
		request.setAttribute("allPurchases", purchases);
		for (Integer confirmationCode : purchases) {
			System.out.println("Hola" + confirmationCode);
		}
		
		return "purchase_list.jsp";
	}
}
