package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.jms.*;
import javax.jms.Queue;

import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.manager.MessageManager;
import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.Appuser;


/*
* Handler para la vista de mensajes en el navegador
*/
public class ReadBrowserMessageHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
						if(listaIdSender.contains(Tmensaje.getInt("senderId"))){
							continue;
						}else {
							listaSender.add(userManager.getUserById(Tmensaje.getInt("senderId")));
							listaIdSender.add(Tmensaje.getInt("senderId"));
							listaMensaje.add(Tmensaje.getString("msg"));
						}

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
		return "messages_notification.jsp";
	}

}
