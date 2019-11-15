package es.uc3m.ecommerce.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.ecommerce.manager.MessageManager;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;


/**
 * Servlet implementation class SendMessageQueueServlet
 */

public class SendMessageQueueHandler implements IHandler {
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MessageManager messageManager = new MessageManager();
		ConnectionFactory ecommerceconnectionfactory = messageManager.connectionfactory;
		Queue queue = messageManager.queue;
		
		try {

			
			// - In the following steps we write the message and send it				
			// First create a connection using the connectionFactory
			Connection con = ecommerceconnectionfactory.createConnection();			      
		      // Next create the session. Indicate that transaction will not be supported
			Session ses = con.createSession(false,Session.AUTO_ACKNOWLEDGE);	
			
			con.start();
			// Now use the session to create a message producer associated to the queue
			MessageProducer producer = ses.createProducer(queue);
			 // Now use the session to create a text message
			TextMessage message = ses.createTextMessage();
			//  We retrieve the parameter 'message' from the request, and use it as text of our message
			message.setText(request.getParameter("message"));
			// Use the message producer to send the message						messageProducer.send(textMessage);
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
	
			
		}catch (Exception e) {
			System.out.println(
				"JHC *************************************** Error in doPost: "
					+ e.toString());		
		}
	return "messages_1to1.jsp";
	}

}
