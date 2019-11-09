package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;

public class ShowProfileHandler implements IHandler {
	
	@Override 
	public String handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,IOException {
		
		// TODO Auto-generated method stub
		UserManager userManager = new UserManager();
		//HttpSession sesion = request.getSession();
		
		Appuser appuser = userManager.getUserById(3);
				
		request.setAttribute("user", appuser);		
 
		return "profile.jsp";
}
	

}
