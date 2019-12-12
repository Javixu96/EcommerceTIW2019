package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uc3m.ecommerce.model.Appuser;

/*
* Handler para registrar un nuevo usuario comprador / vendedor
*/
public class RegisterRequestHandler implements IHandler {
	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;
	
	private EntityManagerFactory emf;
	private UserTransaction ut;
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:13101");
		WebTarget webTargetPath;
		Response responsews;
		String viewURL = null;

		// Tomo los parametros del form de register
		String introducedName = request.getParameter("register_name");
		String introducedSurname = request.getParameter("register_surname");
		String introducedAddress = request.getParameter("register_address");
		String introducedEmail = request.getParameter("register_email");
		String introducedPassword = request.getParameter("register_password");
		int introducedRole = Integer.parseInt(request.getParameter("register_role"));
			
		// Retorno la lista de usuarios que tienen ese email (deber ser de tamaño 0 o 1)
		
		List<Appuser> userListByName=new ArrayList<Appuser>();
		webTargetPath = webTarget
				.path("users/login").queryParam("email",introducedEmail);
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
		responsews = invocationBuilder.get();
		
		Appuser[] allUsers=responsews.readEntity(Appuser[].class);
		for(int i=0;i<allUsers.length;i++) {
			userListByName.add(allUsers[i]);
		}
		System.out.println("UserListByName returned by manager when checking if email already registered is size " + userListByName.size());
		
		// Si el email ya esta registrado
		if(userListByName.size() != 0) {
			// Redirijo a register.jsp con un mensaje marcado con un atributo de request 
			request.setAttribute("alreadyRegisteredError", 1);
			viewURL = "register.jsp";
		// Si el email no estÃ¡ registrado
		} else {
			
			// El id se genera automaticamente cuando se pone una nueva tupla en la tabla 
			Appuser user=new Appuser();
			user.setEmail(introducedEmail);
			user.setPostalAddress(introducedAddress);
			user.setPw(introducedPassword);
			user.setUserName(introducedName);
			user.setUserSurnames(introducedSurname);
			user.setUserRole(introducedRole);
			user.setIsDeleted(0);
			webTargetPath = webTarget
					.path("users/login").queryParam("email",introducedEmail);
			invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
			responsews = invocationBuilder.post(Entity.entity(user,MediaType.APPLICATION_JSON));
			
			// Registro completado con exito -> redirijo al login con un mensaje basado en un atributo de request
			request.setAttribute("registrationSuccess", 1);
			viewURL = "login.jsp";
		}
		return viewURL;
	}

}
