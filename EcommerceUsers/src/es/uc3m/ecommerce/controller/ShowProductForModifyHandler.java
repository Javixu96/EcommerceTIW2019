package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import es.uc3m.ecommerce.manager.CategoryManager;
import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.model.Product;
import es.uc3m.ecommerce.model.Category;

public class ShowProductForModifyHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//contador para saber cual es el producto que queremos modificar
		int counter=Integer.parseInt(request.getParameter("contadorModi"));
		
		HttpSession mySession = request.getSession(true);
		
		Product product = (Product)mySession.getAttribute("productToModify"+counter);
		
		request.setAttribute("product", product);
	
		return "modif_product.jsp";
	}
}