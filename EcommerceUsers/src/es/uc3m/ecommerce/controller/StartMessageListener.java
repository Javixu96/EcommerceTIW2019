package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.Random;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.ecommerce.manager.MessageManager;
import es.uc3m.ecommerce.model.Purchas;

public class StartMessageListener implements MessageListener {

	MessageManager messageManager;
	ConnectionFactory tiwconnectionfactory;
	Queue queue;
	Connection con;
	Session ses;
	MessageConsumer consumer;
	
	public void start() throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		messageManager = new MessageManager();
		tiwconnectionfactory = messageManager.connectionfactory;
		queue = messageManager.queue;
		
		try {
			// First create a connection using the connectionFactory
			con = tiwconnectionfactory.createConnection();
		      
		    // Next create the session. Indicate that transaction will not be supported
			ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			con.start();
		 			   
			consumer = ses.createConsumer(queue);
			
			//Start the connection
			con.start();
			consumer.setMessageListener(this);
			
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
		// return "orderConfirmed.jsp";
	}

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub

		if (message != null) {
			MapMessage map = (MapMessage) message;
			
			try {
				System.out.println("       Message: "+ map.getString("card") + " </br>");
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				System.out.println("error");
				e.printStackTrace();
			}
					
			Purchas order = new Purchas();
			int randomNumber = 10000 + new Random().nextInt(90000);
					
			order.setConfirmationCode(randomNumber);
			
			// SAVE CONFIRMATION CODE TO BBDD
			//
			//
		} 
	}
	
	public void stop() throws JMSException {
		// Close session
		ses.close();
					
		// Close connection
		con.close();

		// Close the producer
		consumer.close();
	}
}
