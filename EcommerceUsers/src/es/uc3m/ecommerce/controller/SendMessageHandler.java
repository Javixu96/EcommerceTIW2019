package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.ecommerce.manager.MessageManager;
import es.uc3m.ecommerce.model.Purchas;

public class SendMessageHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
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
			message.setString("msg", "Nuevo mensaje");
			message.setString("sender", "Sender Email or Name");
			/*
			 * WAITING FOR THE PARAMETER 'message' FROM THE JSP FORM
			 * 
			 * */
			
			// message.setText("message", request.getParameter("message"));
			
			
			
			// Setting a message property in order to filter in the listener
			
			/*
			 * WAITING FOR THE PARAMETER 'receiverId' FROM THE JSP FORM
			 * 
			 * */
			
			// message.setStringProperty("sendTo", request.getParameter("receiverId"));
			message.setStringProperty("sendTo", "4");
			
			/*
			 * 
			 * IF THE SENDER IS A SELLER AND WANTS TO SEND A MSG TO ALL USERS, WE NEED TO SET A
			 * NEW ATTRIBUTE
			 * 
			 * */
			
			// message.setStringProperty("type", "broadcast");
			
			System.out.println("*** SEND MESSAGE HANDLER" + message.getString("name"));
			
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
		return "index.jsp";
	}

}
