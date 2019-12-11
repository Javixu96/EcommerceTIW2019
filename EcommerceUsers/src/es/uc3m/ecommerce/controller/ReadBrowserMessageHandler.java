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

		int status = resp.getStatus();

		Message[] miMensajes= resp.readEntity(Message[].class);
		List<Appuser> listaSender=new ArrayList<Appuser>();
		List<Integer> listaIdSender=new ArrayList<Integer>();
		List<String> listaMensaje=new ArrayList<String>();
		for(int i=0;i<miMensajes.length;i++) {
			Message msg=(Message)miMensajes[i];
			if(listaIdSender.contains(msg.getSender().getUserId())){
				continue;
			}else {
				if(user.getUserRole()!=2 && msg.getBroadcast()==1) {
					continue;
				}
				listaSender.add(msg.getSender());
				listaIdSender.add(msg.getSender().getUserId());
				listaMensaje.add(msg.getMessage());
			}			
		}
		request.setAttribute("listaMensaje", listaMensaje);
		request.setAttribute("listaSender", listaSender);
		/*
		MessageManager messageManager = new MessageManager();
		ConnectionFactory tiwconnectionfactory = messageManager.connectionfactory;
		Queue queue = messageManager.queue;
		UserManager userManager = new UserManager();
		try {
			//Create the connection JMS
			Connection connection = tiwconnectionfactory.createConnection();

			boolean bTransacted = false;

			int iAcknowledgeMode = Session.CLIENT_ACKNOWLEDGE;

			//Create the session JMS
			Session session = connection.createSession(bTransacted, iAcknowledgeMode);
			
			HttpSession ses = request.getSession();
			Appuser user = (Appuser) ses.getAttribute("user");
		 		
			QueueBrowser browser;
			//Tipo de mensaje: broadcast o dirigido a un usuario concreto
			String selector = "(type='" + "broadcast" + "') "
					+ "OR "
					+ "(sendTo=" + user.getUserId() + ")";
			
			browser = session.createBrowser(queue,selector);
			
			//Start the connection
			connection.start();

			Enumeration enum1 = browser.getEnumeration();
			//Se crea 3 listas, uno para mensaje, otro para appuser, y otro para los id de appuser porque .contains no funciona bien
			List<String> listaMensaje=new ArrayList<String>();
			List<Integer> listaIdSender=new ArrayList<Integer>();
			List<Appuser> listaSender=new ArrayList<Appuser>();
			while (enum1.hasMoreElements()) {
				Object message = enum1.nextElement();
				if (message != null) {
					if (message instanceof MapMessage) {
						MapMessage Tmensaje =
							(MapMessage) message;
						//si es el segundo o posterior mensaje del mismo ususario
						


					}
				}
				
			}
			request.setAttribute("listaMensaje", listaMensaje);
			request.setAttribute("listaSender", listaSender);
			// Stop connection
			connection.stop();

			// Close browser
			browser.close();

			// Close session
			session.close();

			// Close connection
			connection.close();
			
		} catch (JMSException e) {
				System.out.println(
					"JHC *************************************** Error in doPost: "
						+ e);					
			}
			*/
		return "messages_notification.jsp";
	}

}
