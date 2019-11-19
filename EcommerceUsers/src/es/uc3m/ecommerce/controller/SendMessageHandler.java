package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.manager.MessageManager;
import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Purchas;

public class SendMessageHandler implements IHandler {

	//true->1to1 false->Broadcast
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
		// TODO Auto-generated method stub
		
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
			/*
			 * WAITING FOR THE PARAMETER 'message' FROM THE JSP FORM
			 * 
			 * */	
			message.setString("msg", request.getParameter("message"));
			message.setInt("senderId", user.getUserId());

			// Setting a message property in order to filter in the listener
			
			/*
			 * WAITING FOR THE PARAMETER 'receiverId' FROM THE JSP FORM
			 * 
			 * */
			Appuser seller=(Appuser)session.getAttribute("sender");
			request.setAttribute("sender", seller);

			message.setIntProperty("sendTo", seller.getUserId());

			message.setIntProperty("sendFrom", user.getUserId());

			
			/*
			 * 
			 * IF THE SENDER IS A SELLER AND WANTS TO SEND A MSG TO ALL USERS, WE NEED TO SET A
			 * NEW ATTRIBUTE
			 * 
			 * */
			
			// message.setStringProperty("type", "broadcast");
			
			
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
		
		return "messages_1to1.jsp";
	}
	
	public String processBroadcast(HttpServletRequest request)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
				/*
				 * WAITING FOR THE PARAMETER 'message' FROM THE JSP FORM
				 * 
				 * */	
				message.setString("msg", mensajeTosend);
				message.setInt("senderId", user.getUserId());

				// Setting a message property in order to filter in the listener
				
				/*
				 * WAITING FOR THE PARAMETER 'receiverId' FROM THE JSP FORM
				 * 
				 * */
				request.setAttribute("sender", seller);

				message.setIntProperty("sendTo", seller.getUserId());

				message.setIntProperty("sendFrom", user.getUserId());

				
				/*
				 * 
				 * IF THE SENDER IS A SELLER AND WANTS TO SEND A MSG TO ALL USERS, WE NEED TO SET A
				 * NEW ATTRIBUTE
				 * 
				 * */
				
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
		
		return "messages_broadcast.jsp";
	}
	
	
	

}
