package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.model.Product;

public class ShowProductHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserManager userManager = new UserManager();
		//HttpSession sesion = request.getSession();
		Product products = userManager.getElementById(3);
		System.out.println("hola"+products.getProductName());
		request.setAttribute("productName", products);
		
		return "index.jsp";
	}

<<<<<<< HEAD
}
=======
}
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef
