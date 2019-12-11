package es.uc3m.ecommerce.controller;

import java.io.IOException;

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
import es.uc3m.ecommerce.model.Purchas;

/*
* Handler que gestiona el envío de mensajes
*/
public class SendMessageHandler implements IHandler {
	
		Client client;
		WebTarget webTarget;
		WebTarget webTargetPath;
		Invocation.Builder invocationBuilder;
		Response resp;	

	//true->1to1 false->Broadcast(para todos los compradores)
		private boolean toOneOrBroadcast;
		
		public SendMessageHandler (boolean toOneOrBroadcast) {
			this.toOneOrBroadcast = toOneOrBroadcast;
		}
		
		@Override
		public String handleRequest(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
			return toOneOrBroadcast ? processToOne(request) : processBroadcast(request);
		}
	
	public String processToOne(HttpServletRequest request)
			throws ServletException, IOException {

		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:13103");
		String path = "message";
		webTargetPath = webTarget.path(path);
		
		Message msg = new Message();
		HttpSession session = request.getSession();
		Appuser user = (Appuser) session.getAttribute("user");
		Appuser seller=(Appuser)session.getAttribute("sender");
		msg.setSender(user);
		msg.setReceiver(seller);
		msg.setMessage(request.getParameter("message"));
		msg.setIsRead(0);
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
		resp= invocationBuilder.post(Entity.entity(msg,MediaType.APPLICATION_JSON));
		
		return "messages_1to1.jsp";
	}
	
	public String processBroadcast(HttpServletRequest request)
			throws ServletException, IOException {

		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:13101");
		String path = "users/buyers";
		webTargetPath = webTarget.path(path);
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
		resp= invocationBuilder.get();
		
		if(resp.getStatus()==200) {
			Appuser[] buyers= resp.readEntity(Appuser[].class);
	
			HttpSession session = request.getSession();
			Appuser user = (Appuser) session.getAttribute("user");
			for(int i=0;i<buyers.length;i++) {
				System.out.println(buyers[i].getUserName());
				Message msg = new Message();
				msg.setSender(user);
				msg.setReceiver(buyers[i]);
				msg.setMessage(request.getParameter("message"));
				msg.setIsRead(0);
				webTarget = client.target("http://localhost:13103");
				path = "message";
				webTargetPath = webTarget.path(path);
				invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
				resp= invocationBuilder.post(Entity.entity(msg,MediaType.APPLICATION_JSON));
			}
		}
		return "messages_broadcast.jsp";
	}
	
	
	

}
