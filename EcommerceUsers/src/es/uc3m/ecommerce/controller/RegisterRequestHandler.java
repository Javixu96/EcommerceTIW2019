package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.Appuser;

public class RegisterRequestHandler implements IHandler {
	
	private EntityManagerFactory emf;
	private UserTransaction ut;
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String viewURL = null;
		
		UserManager manager = new UserManager();

		String introducedName = request.getParameter("register_name");
		String introducedSurname = request.getParameter("register_surname");
		String introducedAddress = request.getParameter("register_address");
		String introducedEmail = request.getParameter("register_email");
		String introducedPassword = request.getParameter("register_password");
		int introducedRole = Integer.parseInt(request.getParameter("register_role"));
				
		System.out.println("introduced values");
		System.out.println(introducedName);
		System.out.println(introducedSurname);
		System.out.println(introducedAddress);
		System.out.println("email" + introducedEmail);
		System.out.println(introducedPassword);

		List<Appuser> userListByName = manager.findByEmail(introducedEmail);
		System.out.println("UserListByName returned by manager when checking if email already registered is size " + userListByName.size());
		if(userListByName.size() != 0) {
			// Email already registered -> redirect to register.jsp with JS warning
			System.out.println("Email already registered");
			request.setAttribute("alreadyRegisteredError", 1);
			viewURL = "register.jsp";
		} else {
			System.out.println("Email free");
			// El id se genera automaticamente cuando se pone una nueva tupla en la tabla 
			manager.insert(introducedName, introducedSurname, introducedAddress, introducedEmail, introducedPassword, introducedRole);
			
			// Registration complete -> redirect to login.jsp with JS suggestion
			request.setAttribute("registrationSuccess", 1);
			//System.out.println("Registration success. Now " + manager.findByEmail(introducedEmail).getEmail() + " is registered");
			viewURL = "login.jsp";
		}
		return viewURL;
	}

}
