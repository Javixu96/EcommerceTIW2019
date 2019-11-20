package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.manager.MessageManager;
import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;
import es.uc3m.ecommerce.model.Purchas;

/*
*Metodo que gestiona el envio del numero de confirmacion de compra
*/
public class SendOrderMessageHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MessageManager messageManager = new MessageManager();
		ProductManager productManager = new ProductManager();
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
			
			ObjectMessage message2 = ses.createObjectMessage();	
			ObjectMessage message3 = ses.createObjectMessage();	
			ObjectMessage message4 = ses.createObjectMessage();			
			HttpSession session = request.getSession();
			
			List<Product> cartList = (List<Product>) session.getAttribute("cartList");
			
			for(int i = 0; i < cartList.size(); i ++) {
				System.out.println("producto id "+cartList.get(i).getProductId());
				message.setInt("cartList"+i,cartList.get(i).getProductId());
			}
			
			List<Integer> cartQuantities = (List<Integer>) session.getAttribute("cartQuantities");
			for(int i = 0; i < cartQuantities.size(); i ++) {
				System.out.println("producto cantidad "+cartQuantities.get(i));
				message.setInt("cartQuantity"+i,(int)cartQuantities.get(i));
			}
			
			message.setInt("size",cartList.size());
			
			Appuser buyer = (Appuser)session.getAttribute("user");
			int buyerId =buyer.getUserId();		
			message.setInt("buyer", buyerId);
		
			int total = (int)session.getAttribute("cartTotal");
			String card = request.getParameter("card");
			
			message.setString("card", card);
			message.setInt("total", total);
		
			/*
			message2.setObject(buyer);
			message3.setObject((Serializable) cartList);
			message4.setObject((Serializable) cartQuantities);	
			
			message2.setJMSMessageID("buyer");
		
			message2.setStringProperty("id", "buyer");	
			message3.setStringProperty("id", "cartList");
			message4.setStringProperty("id", "cartQuantities");
			*/
			for(int i = 0; i < cartList.size(); i ++) {
				cartList.get(i).setStock(cartList.get(i).getStock()-cartQuantities.get(i));
				
				try {
					productManager.modifyProduct(cartList.get(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}			
			// Setting a message property in order to filter in the listener
			message.setStringProperty("type", "orderConfirmationCode");
			/*
			message2.setStringProperty("type", "orderConfirmationCode");
			message3.setStringProperty("type", "orderConfirmationCode");
			message4.setStringProperty("type", "orderConfirmationCode");
			*/
			// Use the message producer to send the message	messageProducer.send(textMessage);
			producer.send(message);
			System.out.println("message send");
			//producer.send(message2);
			//producer.send(message3);
			//producer.send(message4);

			// Close the producer
			producer.close();
			/*
			cartList.clear();
			cartQuantities.clear();
			
			session.setAttribute("cartList", cartList);
			session.setAttribute("cartQuantities", cartQuantities);
			*/
			// Close the session 
			ses.close(); 
			
			// Close the connection 
			con.close();	
		} catch (javax.jms.JMSException e) {
				System.out.println(
					"JHC *************************************** Error in doPost: "
						+ e);
				
		}
		return "index.jsp";
	}

}
