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

		int status = resp.getStatus();
		
		Message ms= resp.readEntity(Message.class);
		
		System.out.println(status);
		System.out.println(ms.getMessage());
		
		/*
		MessageManager messageManager = new MessageManager();
		ConnectionFactory tiwconnectionfactory = messageManager.connectionfactory;
		Queue queue = messageManager.queue;
		
		try {
			// First create a connection using the connectionFactory
			Connection con = tiwconnectionfactory.createConnection();
		      
		    // Next create the session. Indicate that transaction will not be supported
			Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			con.start();
			
			// Now use the session to create a message producer associated to the queue
			MessageProducer producer = ses.createProducer(queue);
			
			
			// Now use the session to create a map message
			MapMessage message = ses.createMapMessage();
			
			HttpSession session = request.getSession();
			Appuser user = (Appuser) session.getAttribute("user");

			message.setString("msg", request.getParameter("message"));
			message.setInt("senderId", user.getUserId());

			Appuser seller=(Appuser)session.getAttribute("sender");
			request.setAttribute("sender", seller);
			
			//atributos para filtrar
			message.setIntProperty("sendTo", seller.getUserId());
			message.setIntProperty("sendFrom", user.getUserId());
						
			// Use the message producer to send the message	messageProducer.send(textMessage);
			producer.send(message);

			// Close the producer
			producer.close();
			
			// Close the session 
			ses.close(); 
			
			// Close the connection 
			con.close();	
		} catch (javax.jms.JMSException e) {
				System.out.println(
					"JHC *************************************** Error in doPost: "
						+ e);
				System.out.println(
					"JHC *************************************** Error MQ: "
						+ e.getLinkedException().getMessage());
				System.out.println(
					"JHC *************************************** Error MQ: "
						+ e.getLinkedException().toString());		
				System.out.println(" Error when sending the message</BR>");
		
				
			}catch (Exception e) {
				System.out.println(
					"JHC *************************************** Error in doPost: "
						+ e.toString());
				System.out.println(" Error when sending the message</BR>");
		}
		*/
		return "messages_1to1.jsp";
	}
	
	public String processBroadcast(HttpServletRequest request)
			throws ServletException, IOException {

		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:13103");
		String path = "message";
		webTargetPath = webTarget.path(path);
		
		Message msg = new Message();
		HttpSession session = request.getSession();
		Appuser user = (Appuser) session.getAttribute("user");
		msg.setSender(user);
		msg.setMessage(request.getParameter("message"));
		msg.setIsRead(0);
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
		resp= invocationBuilder.post(Entity.entity(msg,MediaType.APPLICATION_JSON));

		int status = resp.getStatus();
		
		Message ms= resp.readEntity(Message.class);
		
		System.out.println(status);
		System.out.println(ms.getMessage());
		/*
		MessageManager messageManager = new MessageManager();
		ConnectionFactory tiwconnectionfactory = messageManager.connectionfactory;
		Queue queue = messageManager.queue;
		UserManager us = new UserManager();
		
		try {
			// First create a connection using the connectionFactory
			Connection con = tiwconnectionfactory.createConnection();
		      
		    // Next create the session. Indicate that transaction will not be supported
			Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			con.start();
			
			// Now use the session to create a message producer associated to the queue
			MessageProducer producer = ses.createProducer(queue);
			
			List<Appuser> buyersList = us.getBuyers();
			
			String mensajeTosend = request.getParameter("message");
			
			for(Appuser seller:buyersList) {
				// Now use the session to create a map message
				MapMessage message = ses.createMapMessage();
				
				HttpSession session = request.getSession();
				Appuser user = (Appuser) session.getAttribute("user");

				message.setString("msg", mensajeTosend);
				message.setInt("senderId", user.getUserId());

				// Setting a message property in order to filter in the listener

				request.setAttribute("sender", seller);

				message.setIntProperty("sendTo", seller.getUserId());

				message.setIntProperty("sendFrom", user.getUserId());

				
				// message.setStringProperty("type", "broadcast");
				
				
				// Use the message producer to send the message	messageProducer.send(textMessage);
				producer.send(message);
			}		

			// Close the producer
			producer.close();
			
			// Close the session 
			ses.close(); 
			
			// Close the connection 
			con.close();	
		} catch (javax.jms.JMSException e) {
				System.out.println(
					"JHC *************************************** Error in doPost: "
						+ e);
				System.out.println(
					"JHC *************************************** Error MQ: "
						+ e.getLinkedException().getMessage());
				System.out.println(
					"JHC *************************************** Error MQ: "
						+ e.getLinkedException().toString());		
				System.out.println(" Error when sending the message</BR>");
		
				
			}catch (Exception e) {
				System.out.println(
					"JHC *************************************** Error in doPost: "
						+ e.toString());
				System.out.println(" Error when sending the message</BR>");
		}
		*/
		return "messages_broadcast.jsp";
	}
	
	
	

}
