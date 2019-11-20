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
	
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "";
	}
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
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		if (message != null ) {
			
			if (message instanceof MapMessage) {
			
				MapMessage m = (MapMessage) message;
				int randomNumber = 10000 + new Random().nextInt(90000);
				
				try {
				
				int buyerId = m.getInt("buyer");
				int size = m.getInt("size");
				
				for(int i = 0; i < size; i ++) {
					System.out.println("aaaaaaaaaaaaaaaa"+size);
					
					Purchas order = new Purchas();
					
					int productId=m.getInt("cartList"+i);
					System.out.println(productId);
					
					Product product=productManager.findById(productId);
					System.out.println(product.getProductName());
					order.setProduct(product);
					System.out.println("cccccccccccccccc");
					
					int cantidad=m.getInt("cartQuantity"+i);
					System.out.println(cantidad);
					order.setProductQuantity(cantidad);					
					System.out.println("ddddddddddddd");
					
					
					order.setAppuser(userManager.getUserById(buyerId));
					System.out.println("eeeeeeeeeeee");
					
					
					order.setConfirmationCode(randomNumber);
					
					try {
						System.out.println(order.getProductQuantity());
						purchaseManager.create(order);
					} catch (Exception e) {
								// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				}
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
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
		
	
			// SAVE CONFIRMATION CODE TO BBDD
			//
			//
		
	}
	
	public void stop() throws JMSException {
		// Close session
		ses.close();
					
		// Close connection
		con.close();

		// Close the producer
		consumer.close();
	}
	/*
	if (message instanceof MapMessage) {
		MapMessage m = (MapMessage) message;
		try {
			List<Product> cartList = (List<Product>)m.getObject("cartList");
			List<Integer> cartQuantities = (List<Integer>) m.getObject("cartQuantities");
			Appuser buyer = (Appuser)m.getObject("buyer");
			int randomNumber = 10000 + new Random().nextInt(90000);
			System.out.println("bbbbbbbbbbbbbbbbbbbbbb");
			
			for(int i = 0; i < cartList.size(); i ++) {
				Purchas order = new Purchas();
				order.setProduct(cartList.get(i));
				order.setProductQuantity(cartQuantities.get(i));
				order.setAppuser(buyer);
				order.setConfirmationCode(randomNumber);
				try {
					purchaseManager.create(order);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}else
		*/
	/*
				try {	
					if(m.getStringProperty("id").equals("buyer")) {
						System.out.println("BuyerReady");
						System.out.println("buyer"+buyerReady);
						System.out.println("list"+cartListReady);
						System.out.println("cantidad" +cartQuantitiesReady);
						buyer=(Appuser)m.getObject();
						buyerReady=true;
					}else if (m.getStringProperty("id").equals("cartList")) {
						System.out.println("listReady");
						System.out.println("buyer"+buyerReady);
						System.out.println("list"+cartListReady);
						System.out.println("cantidad" +cartQuantitiesReady);
						cartList = (List<Product>)m.getObject();
						cartListReady=true;
					}else if (m.getStringProperty("id").equals("cartQuantities")) {
						System.out.println("cantidadready");
						System.out.println("buyer"+buyerReady);
						System.out.println("list"+cartListReady);
						System.out.println("cantidad" +cartQuantitiesReady);
						cartQuantities = (List<Integer>) m.getObject();
						cartQuantitiesReady=true;
					}
					*/
}
