package es.uc3m.ecommerce.controller;

import java.io.IOException;

import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uc3m.ecommerce.manager.MessageManager;
import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Message;


/*
* Handler para la vista de mensajes en el navegador
*/
public class ReadBrowserMessageHandler implements IHandler {
	
	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;	
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:13103");
		HttpSession session = request.getSession();
		Appuser user = (Appuser) session.getAttribute("user");
		String path = "message"+"/"+user.getUserId();
		webTargetPath = webTarget.path(path);
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
		resp= invocationBuilder.get();
		
		List<Appuser> listaSender=new ArrayList<Appuser>();
		List<Integer> listaIdSender=new ArrayList<Integer>();
		List<String> listaMensaje=new ArrayList<String>();
		if(resp.getStatus()==200) {
			Message[] miMensajes= resp.readEntity(Message[].class);
			for(int i=0;i<miMensajes.length;i++) {
				Message msg=(Message)miMensajes[i];
				if(listaIdSender.contains(msg.getSender().getUserId())){
					continue;
				}else {
					listaSender.add(msg.getSender());
					listaIdSender.add(msg.getSender().getUserId());
					listaMensaje.add(msg.getMessage());
				}			
			}
		}
		request.setAttribute("listaMensaje", listaMensaje);
		request.setAttribute("listaSender", listaSender);
		return "messages_notification.jsp";
	}

}
