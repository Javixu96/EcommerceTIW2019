package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uc3m.ecommerce.model.*;

/*
* Handler para la modificacion del perfil de usuario
*/
public class ModifyProfileHandler implements IHandler {

	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;	
	
	//true->modify false->delete
	private boolean modifyOrDelete;
	
	public ModifyProfileHandler (boolean modifyOrDelete) {
		this.modifyOrDelete = modifyOrDelete;
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		client = ClientBuilder.newClient();
		return modifyOrDelete ? processModify(request) : processDelete(request);
	}
	
	
	public String processModify(HttpServletRequest request)
			throws ServletException, IOException { 
		webTarget = client.target("http://localhost:13101");
		String path = "users";
		webTargetPath = webTarget.path(path);
		
		
		String userName = request.getParameter("profile_name");
		String userSurnames = request.getParameter("profile_surnames");
		String userEmail = request.getParameter("profile_email");
		String userAddress = request.getParameter("profile_address");
		
		
		HttpSession session = request.getSession();
		Appuser user = (Appuser) session.getAttribute("user");

		user.setUserName(userName);
		user.setUserSurnames(userSurnames);
		user.setEmail(userEmail);
		user.setPostalAddress(userAddress);
		
		//foto no obligatoria
		Part filePart = request.getPart("fileToUpLoad");
		if((int) filePart.getSize()!=0) {
			 byte[] data = new byte[(int) filePart.getSize()];
			 filePart.getInputStream().read(data, 0, data.length);
			 user.setUserPicture(data);
		}
		
		//comprobacion del password
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String newPwRepeat = request.getParameter("newPwRepeat");
		
		if(!oldPassword.isEmpty()) {
			if(!oldPassword.equals(user.getPw())) {
				request.setAttribute("invalidCredentialsError", 1);
			}else {
				if(!newPassword.equals(newPwRepeat)) {
					request.setAttribute("invalidRepeatError", 1);
				}else {
					user.setPw(newPassword);
				}
			}
		}else {
			request.setAttribute("invalidCredentialsError", null);
			request.setAttribute("invalidRepeatError", null);
		}
		
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
		resp= invocationBuilder.put(Entity.entity(user,MediaType.APPLICATION_JSON));	
		
		return "profile.html";
	}
	
	
	public String processDelete(HttpServletRequest request) { 
		
		webTarget = client.target("http://localhost:13101");
		
		HttpSession session = request.getSession();
		Appuser user = (Appuser) session.getAttribute("user");
		String path = "users"+"/"+user.getUserId();
		webTargetPath = webTarget.path(path);
		
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
		resp= invocationBuilder.delete();
		
		
		return "loggingin.html";
	}

}