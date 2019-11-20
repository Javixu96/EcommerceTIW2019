package es.uc3m.ecommerce.manager;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


//Clase que crea los elementos necesarios para JMS: conexion, cola, y tema
public class MessageManager {

	public ConnectionFactory connectionfactory;
	public Queue queue;
	public Topic topic;
	
	public MessageManager() {
		try {
			Context ctx = new InitialContext();
			//
			connectionfactory = (ConnectionFactory) ctx.lookup("tiwconnectionfactory");

			queue = (Queue) ctx.lookup("ecommercequeue"); //
			topic = (Topic) ctx.lookup("EcommerceTopic");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} 
