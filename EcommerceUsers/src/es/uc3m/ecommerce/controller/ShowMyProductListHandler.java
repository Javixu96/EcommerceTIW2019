package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;

public class ShowMyProductListHandler implements IHandler {
	
	@Override 
	public String handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,IOException {
		
		// TODO Auto-generated method stub
		UserManager userManager = new UserManager();
		ProductManager im = new ProductManager();
		//HttpSession sesion = request.getSession();
		
		Appuser appuser = userManager.getUserById(3);
		List<Product> resultado=null;
		try {
			resultado=im.findAllByAppuser(appuser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		request.setAttribute("allProducts", resultado);		
 
		return "product_list_seller.jsp";
}
	

}
