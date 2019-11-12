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
	    handlerHash.put("/profile.html", new ShowProfileHandler());
	    handlerHash.put("/index.html", new ShowProductHandler());
	    handlerHash.put("/modifyUser.html", new ModifyProfileHandler());
	    handlerHash.put("/insert_product.html", new InsertProductHandler());
	    handlerHash.put("/shop.html", new ShowAllProductsHandler());
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
					System.out.println("En el Handler: mandando a la vista " + viewURL);
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