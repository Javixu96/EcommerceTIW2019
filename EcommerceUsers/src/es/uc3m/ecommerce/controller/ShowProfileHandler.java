package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Message;
import es.uc3m.ecommerce.model.Product;

/*
 * Handler que muestra la vista de perfil de usuario
*/
public class ShowProfileHandler implements IHandler {
	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;
	
	@Override 
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
			
		client = ClientBuilder.newClient();
		HttpSession session = request.getSession();
		Appuser appuser = (Appuser) session.getAttribute("user");
		String viewURL = null; 
	
		
		if(session.getAttribute("user") == null) {
			viewURL = new ForbiddenPageHandler().handleRequest(request, response);
			return viewURL;
		} else {		
			session.setAttribute("user", appuser);
			viewURL = "profile.jsp";
		}
		String path = "users/sellers";
		webTarget = client.target("http://localhost:13101");		
		webTargetPath = webTarget.path(path);
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);
		resp = invocationBuilder.get();
		
		if(resp.getStatus()==200) {
			Appuser[] buyers= resp.readEntity(Appuser[].class);

			for(int i=0;i<buyers.length;i++) {
				if(buyers[i].getUserId()==appuser.getUserId()) {
					request.setAttribute("isSeller", 1);
					return viewURL;
				}
			}
			request.setAttribute("isSeller", null);
		}
		
		return viewURL;
		
	}
	

}
