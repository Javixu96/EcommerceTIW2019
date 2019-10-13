package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uc3m.ecommerce.controller.LoginRequestHandler;


/* FRONT CONTROLLER -> Captura todas las llamadas a cualquier .html */

@WebServlet("/login.jsp")
public class FrontControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ServletContext appContext;
	
	// Hash table of RequestHandler instances, keyed by request URL
	private Map handlerHash;
	
	// Initiate DB -> simulated by a HashMap made persistent by saving it as Application Attribute
    DataStore db; 


    public FrontControllerServlet() {
        super();
    }
    
	// Initialize mappings: not implemented here
	public void init(ServletConfig config) throws ServletException {
		// This will read mapping definitions and populate handlerHash
		handlerHash = new HashMap();
	    handlerHash.put("/login.jsp", new LoginRequestHandler());
	    handlerHash.put("/register.jsp", new RegisterRequestHandler());
	    
	    // Initiate app context to store persisten attributes like HashMap for DB simulation
	    appContext = config.getServletContext();
		appContext.setAttribute("db", db);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtain path of the request
		String path = request.getServletPath();
		
		// Select handler for that path (if exists)
		if(handlerHash.containsKey(path)) {
			RequestHandler handler = (RequestHandler) handlerHash.get(path);
			// Call the method handleRequsest of the instance in order to obtain the url 
			String viewURL = handler.handleRequest(request, response, appContext);
		      
			// Dispatch the request to the url obtained
			if(viewURL != null) {
				request.getRequestDispatcher(viewURL).forward(request, response);
			}
			else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}
		else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
