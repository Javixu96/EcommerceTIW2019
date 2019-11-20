package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*
* handler para redigirir en el caso de estar intentando acceder una pagina de acceso restringido
*/
public class ForbiddenPageHandler implements IHandler{

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Cuando se intenta visitar una pagina restringida por login, se redirige a este
		String viewURL = "login.jsp"; 
		// Atributo de request para lanzar el warning JS en la vista login.jsp
		request.setAttribute("needsLoginError", 1);
		
		return viewURL;
	}

}
