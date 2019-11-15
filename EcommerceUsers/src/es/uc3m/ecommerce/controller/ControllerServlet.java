package es.uc3m.ecommerce.controller;


import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.HashMap;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet({"*.html"})
@MultipartConfig
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "EcommerceUsersJPA",name = "jpa/pc")
	private EntityManager em;
       
	// Hash table of RequestHandler instances, keyed by request URL
	  private Map<String,IHandler> handlerHash = new HashMap<String,IHandler>();

	  // Initialize mappings: not implemented here
	  public void init() throws ServletException {

	    // This will read mapping definitions and populate handlerHash
	    handlerHash.put("/profile.html", new es.uc3m.ecommerce.controller.ShowProfileHandler());
	    handlerHash.put("/modifyUser.html", new es.uc3m.ecommerce.controller.ModifyProfileHandler(true));
	    handlerHash.put("/deleteUser.html", new es.uc3m.ecommerce.controller.ModifyProfileHandler(false));
	    
	    
	    handlerHash.put("/product_list_seller.html", new es.uc3m.ecommerce.controller.ShowMyProductListHandler());
	    handlerHash.put("/insert_product.html", new es.uc3m.ecommerce.controller.InsertProductHandler());
	    handlerHash.put("/modify_product.html", new es.uc3m.ecommerce.controller.ModifyProductHandler(true));
	    handlerHash.put("/deleteProduct.html", new es.uc3m.ecommerce.controller.ModifyProductHandler(false));
	    handlerHash.put("/modif_product.html", new es.uc3m.ecommerce.controller.ShowProductForModifyHandler());
	    
	    handlerHash.put("/shop.html", new es.uc3m.ecommerce.controller.ShowAllProductsHandler());
	    
	    handlerHash.put("/sendMessages.html", new es.uc3m.ecommerce.controller.SendMessageQueueHandler());
	    handlerHash.put("/readMessagesQueue.html", new es.uc3m.ecommerce.controller.ReadMessageQueueHandler());
	    handlerHash.put("/readMessagesBrowser.html", new es.uc3m.ecommerce.controller.ReadMessageQueueHandler());
	  }

	  
	  public void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {

		  
		  // Complete. Retrieve from the HashMap the instance of the class which implements the logic of the requested url
		  IHandler rh = (IHandler) handlerHash.get(request.getServletPath());
		  
		  // Complete. If no instance is retrived redirects to error
		  if (rh == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}else {	    
					
				// Complete. Call the method handleRequsest of the instance in order to obtain the url 
					String viewURL = rh.handleRequest(request, response);
					
			  // Complete. Dispatch the request to the url obtained

					if (viewURL == null) {
						// nothing
					} else {
						request.getRequestDispatcher(viewURL).forward(request, response);
					}
			}

	  }
	  public void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
		  doGet(request,response);
		  

	  }
}