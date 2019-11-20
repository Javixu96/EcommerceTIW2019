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
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;

/*
* Handler que prepara la lista de productos a la venta de un determinado vendedor
*/
public class ShowMyProductListHandler implements IHandler {
	
	@Override 
	public String handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,IOException {
		
		HttpSession session = request.getSession();
		Appuser appuser = (Appuser) session.getAttribute("user");

		ProductManager im = new ProductManager();
		
		//se obtiene todos los productos con el manager filtrando por el usuario
		List<Product> resultado=null;
		try {
			resultado=im.findAllByAppuser(appuser);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		request.setAttribute("allProducts", resultado);		
 
		return "product_list_seller.jsp";
}
	

}
