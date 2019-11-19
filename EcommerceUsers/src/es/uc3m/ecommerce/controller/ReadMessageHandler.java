package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.manager.MessageManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Purchas;

public class ReadMessageHandler implements IHandler {

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
			
			// String selector
			// String selector = "sendTo = '" + request.getParameter("userId") + "'";
			HttpSession session = request.getSession();
			Appuser user = (Appuser) session.getAttribute("user");			
			
			Appuser sender = (Appuser)session.getAttribute("sender");
			
			String selector = "(sendTo=" + user.getUserId()+")"
					+ "AND "
					+ "(sendFrom=" + sender.getUserId() + ")";
			
			
			//String selector = "sendTo=" + user.getUserId();
			//Create a consumer
			MessageConsumer mc= ses.createConsumer(queue, selector);
						
			List<String> listaMensaje=new ArrayList<String>();
			
			Message message = null;
			while (true) {
				message = mc.receive(1000);
				
				if (message != null) {
					/*
					 * MapMessage map = (MapMessage) message;
					 * System.out.println("*** READ MESSAGE HANDLER: \n\n Message: "+ map.getString("message"));
					 */
					if (message instanceof MapMessage) {
						MapMessage m = (MapMessage) message;
						listaMensaje.add(m.getString("msg"));
					} else {
						// JHC ************ Not the right type
						break;
					}
				} else // there are no messages
					{
					break;
				}

			}
			request.setAttribute("listaMensaje", listaMensaje);
			session.setAttribute("sender", sender);

			//Close the session
			ses.close();

			//Close the connection
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

}
