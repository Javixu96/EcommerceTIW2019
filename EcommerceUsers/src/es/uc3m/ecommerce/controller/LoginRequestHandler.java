package es.uc3m.ecommerce.controller;

import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.*;

/*
* Handler que se encarga del login del usuario
*/
public class LoginRequestHandler implements IHandler {
	
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String viewURL = null; 
			HttpSession session = request.getSession();

			// LOG OUT -> Tiene que haber un usuario logueado para hacer log out
			if(session.getAttribute("user") != null) {
				// Borramos las variables de sesion del usuario
				session.invalidate();
				// Redirijo a log in 
				viewURL = "login.jsp";
				
			} else {
				
				// Tomo los datos del formulario de log in 
				String introducedEmail  = request.getParameter("register_email");
				String introducedPassword = request.getParameter("register_password");

				
				// Busco en BD una objeto usuario que tenga el mismo email que el introducido
				UserManager manager = new UserManager();
				
				List<Appuser> userListByName = manager.findByEmail(introducedEmail);
				System.out.println("he size of the list of users under that email: " + userListByName.size());
				// Si no existe ese email en BD, el usuario no esta registrado y no puede hacer log in
				if(userListByName.size() != 0) {
					
					Appuser u = userListByName.get(0);
					
					// Si existe el email en BD y la contraseña es correcta: 
					if(u.getPw().equals(introducedPassword)) {

						// Setear el atributo de sesión del usuario logueado
						session.setAttribute("user", u);
						// Redirigir a index.jsp
						viewURL = "index.jsp";
					
					// Si existe el email en BD pero la contraseña es incorrecta:
					} else {
						// Redirigir a login con un atributo de request indicando que la contraseña ha sido incorrecta
						// para informar al usuario de su error en el JSP
						request.setAttribute("passwordError", 1);
						viewURL = "login.jsp";
	
					}
				// Si el email no existe en BD
				} else {
					// Redirigir a login con un atributo de request indicando que el email o está registrado
					// para informar al usuario de su error
					request.setAttribute("notRegisteredError", 1);
					viewURL = "login.jsp";
				}
			}
		// Return name of page to redirect to
		return viewURL;
		
	}

}
