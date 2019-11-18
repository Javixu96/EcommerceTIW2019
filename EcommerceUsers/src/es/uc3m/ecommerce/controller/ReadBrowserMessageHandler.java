package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.jms.*;
import javax.jms.Queue;

import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.ecommerce.manager.MessageManager;
import es.uc3m.ecommerce.model.Purchas;

public class ReadBrowserMessageHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		MessageManager messageManager = new MessageManager();
		ConnectionFactory tiwconnectionfactory = messageManager.connectionfactory;
		Queue queue = messageManager.queue;
		
		try {
			//Create the connection
			Connection connection = tiwconnectionfactory.createConnection();

			boolean bTransacted = false;

			int iAcknowledgeMode = Session.CLIENT_ACKNOWLEDGE;

			//Create the session
			Session session = connection.createSession(bTransacted, iAcknowledgeMode);
		 			   
			QueueBrowser browser;
			
			String selector = "(type='" + "broadcast" + "') "
					+ "OR "
					+ "(sendTo='" + request.getParameter("userId") + "')";
			
			browser = session.createBrowser(queue,selector);

			//Start the connection
			connection.start();

			Enumeration enum1 = browser.getEnumeration();

			Vector vctMessage = new Vector();

			while (enum1.hasMoreElements()) {
				Message message = (Message) enum1.nextElement();
				Message message2 = message;
				if (message2 != null) {
					if (message2 instanceof MapMessage) {
						MapMessage Tmensaje =
							(MapMessage) message2;
						System.out.println("  Message: " +Tmensaje.getString("msg")+"</br>");
					}
				}
			}
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
