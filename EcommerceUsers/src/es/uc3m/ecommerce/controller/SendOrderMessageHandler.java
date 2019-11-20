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
					
			HttpSession session = request.getSession();
			
			//se obtiene la lista de los productos en la sesion
			List<Product> cartList = (List<Product>) session.getAttribute("cartList");
			//para cada producto de la lista, hacemos un setInt de su id en el mensaje
			//que posteriormente los recuperamos en el listener
			for(int i = 0; i < cartList.size(); i ++) {
				message.setInt("cartList"+i,cartList.get(i).getProductId());
			}
			
			//hacemos lo mismo con la lista de cantidades de cada producto comprado
			List<Integer> cartQuantities = (List<Integer>) session.getAttribute("cartQuantities");
			for(int i = 0; i < cartQuantities.size(); i ++) {
				System.out.println("producto cantidad "+cartQuantities.get(i));
			}
			
			//el numero de los productos comprado
			message.setInt("size",cartList.size());
			
			//el comprador
			Appuser buyer = (Appuser)session.getAttribute("user");
			int buyerId =buyer.getUserId();		
			message.setInt("buyer", buyerId);
			
			//el importe total y la tarjeta
			int total = (int)session.getAttribute("cartTotal");
			String card = request.getParameter("card");
			
			message.setString("card", card);
			message.setInt("total", total);
		
			//modificamos el stock de los productos vendidos
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

			// Use the message producer to send the message	messageProducer.send(textMessage);
			producer.send(message);
			
			//borramos las dos listas 
			cartList.clear();
			cartQuantities.clear();
			session.setAttribute("cartList", cartList);
			session.setAttribute("cartQuantities", cartQuantities);
			
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
				
		}
		return "index.jsp";
	}

}
