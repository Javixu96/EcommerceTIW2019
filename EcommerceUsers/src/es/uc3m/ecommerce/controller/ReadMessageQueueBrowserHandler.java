package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadMessageQueueBrowserServlet
 */
@WebServlet({ "/ReadMessageQueueBrowserServlet", "/ReadMessageQueueBrowser.html" })
public class ReadMessageQueueBrowserHandler implements IHandler {
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	return "messages_1to1.jsp";
	}
	private static final long serialVersionUID = 1L;
    
	 // Inject the connectionFactory using annotations
	@Resource (mappedName = "ecommerceconnectionfactory")
	 private ConnectionFactory ecommerceconnectionfactory;
	 // Inject the queue using annotations
	@Resource (mappedName = "ecommercequeue")
	 private Queue queue;
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadMessageQueueBrowserHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			doPost(request, response);
		} catch (Exception e) {
			System.out.println(
				"JHC ***************************************Error in doGet: "
					+ e);
		}		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println(
			"<head><title>An example on how to read messages in browser mode</title></head>");
		out.println("<body>");
		out.println("<H1><U>Reading a message in browser mode</U></H1>");


		try {

			// Create a connection using the connectionFactory
		      Connection con = ecommerceconnectionfactory.createConnection();
		      // Next create the session. Indicate that transaction will not be supported
				
		      Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);	   
			// USe the session to create a browser associated to the queue
		      QueueBrowser bro = ses.createBrowser(queue);
			//Start the connection
		      con.start();
			// User the browser to retrieve a collection (enumeration) of messages
		      java.util.Enumeration enum1 = bro.getEnumeration();
			// java.util.Enumeration enum1 = ..
		      
			while (enum1.hasMoreElements()) {
				// Get a message from the enumeration
				Object obj = enum1.nextElement();

				// Check if the message is an instance of TextMessage
				// If it is an instance of TextMessage, cast it and add it to the out
				if(obj instanceof TextMessage) {
					TextMessage mensajeTxt = (TextMessage) obj;
					out.println("<p>"+mensajeTxt.getText()+"</p>");
				}
				
			}
			// Stop connection   -----------da poco igual!
			con.stop();
			// Close browser
			bro.close();
			// Close session
			ses.close();
			// Close connection
			con.close();
		} catch (Exception e) {
			System.out.println(
				"JHC *************************************** Error in doPost: "
					+ e);
		}

		out.println(
			" >>>>>>  <A href=\"SendMessageToQueue.html\">Back</A></P>");
		out.println("</body></html>");
	}


}
