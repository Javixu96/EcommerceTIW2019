package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.manager.MessageManager;
import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.manager.PurchaseManager;
import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;
import es.uc3m.ecommerce.model.Purchas;

/*
*Handler que gestiona la recogida del mensaje de confirmacion de compra
 * 
 * 
 Este codigo no funciona correctamente debido un error 
 de JMS:DirectConsumer:Caught Exception delivering, 
 en la parte de OnMessage(), concretamente en m.getInt();
 * 
 * 
 * 
 */

public class StartMessageListener implements MessageListener {

	MessageManager messageManager;
	PurchaseManager purchaseManager;
	UserManager userManager;
	ProductManager productManager;
	ConnectionFactory tiwconnectionfactory;
	Queue queue;
	Connection con;
	Session ses;
	MessageConsumer consumer;
	//start
	public void start() throws ServletException, IOException {
		
		messageManager = new MessageManager();
		tiwconnectionfactory = messageManager.connectionfactory;
		queue = messageManager.queue;
		
		try {
			// First create a connection using the connectionFactory
			con = tiwconnectionfactory.createConnection();
		      
		    // Next create the session. Indicate that transaction will not be supported
			ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			con.start();
			
			// Message selector
			String selector = "type='" + "orderConfirmationCode" + "'";
			consumer = ses.createConsumer(queue, selector);
			
			//Start the connection
			con.start();
			consumer.setMessageListener(this);
			
		} catch (javax.jms.JMSException e) {
				System.out.println(
					"JHC *************************************** Error in doPost: "
						+ e);
		}
		// return "orderConfirmed.jsp";
	}

	@Override
	//cuando recibe un mensaje 
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		if (message != null ) {
			// si es de tipo mapmessage
			if (message instanceof MapMessage) {
			
				MapMessage m = (MapMessage) message;
				//genera el codigo de confiramcion
				int randomNumber = 10000 + new Random().nextInt(90000);
				
				try {
					//se obtiene el usuario comprador y numero de productos comprados
					int buyerId = m.getInt("buyer");
					int size = m.getInt("size");
					//para cada uno de los productos comprados
					for(int i = 0; i < size; i ++) {
						//genera un pedido
						Purchas order = new Purchas();
						String cartList="cartList"+i;
						String cartQuantity="cartQuantity"+i;
						
						//se obtiene el producto
						int productId=m.getInt(cartList);
						Product product=productManager.findById(productId);
						order.setProduct(product);
	
						//se obtiene la cantidad
						int cantidad=m.getInt(cartQuantity);
						order.setProductQuantity(cantidad);									
						
						Appuser buyer = userManager.getUserById(buyerId);
						order.setAppuser(buyer);					
						
						order.setConfirmationCode(randomNumber);
						
						//generamos el pedido
						try {
							purchaseManager.create(order);
						} catch (Exception e) {
									// TODO Auto-generated catch block
							e.printStackTrace();
							

						}
					}
				} catch (JMSException e1) {
					System.out.println(
							"JHC *************************************** Error in doPost: "
								+ e1);
						System.out.println(
							"JHC *************************************** Error MQ: "
								+ e1.getLinkedException().getMessage());
						System.out.println(
							"JHC *************************************** Error MQ: "
								+ e1.getLinkedException().toString());		
						System.out.println(" Error when sending the message</BR>");
				}				
			}
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
