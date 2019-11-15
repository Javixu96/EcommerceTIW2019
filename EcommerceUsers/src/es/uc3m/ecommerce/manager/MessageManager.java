package es.uc3m.ecommerce.manager;


import java.util.*;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.model.Category;

public class MessageManager {

	public ConnectionFactory connectionfactory;

	public Queue queue;

	public MessageManager() {
		try {
			Context ctx = new InitialContext();
			// Context ctx = (Context) initCtx.lookup("java:comp");
			connectionfactory = (ConnectionFactory) ctx.lookup("ecommerceconnectionfactory");
			queue = (Queue) ctx.lookup("ecommercequeue");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} 