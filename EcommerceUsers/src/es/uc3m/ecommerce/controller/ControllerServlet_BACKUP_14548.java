package es.uc3m.ecommerce.controller;


import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
<<<<<<< HEAD
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
=======
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
>>>>>>> dev

import es.uc3m.ecommerce.manager.CategoryManager;
import es.uc3m.ecommerce.model.Category;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

<<<<<<< HEAD

@WebServlet(urlPatterns = {"*.html"})
@MultipartConfig
public class ControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ServletContext servletContext;
	
	@PersistenceContext(unitName = "EcommerceUsersPU",name = "jpa/pc")
	private EntityManager em;
=======
/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet({"*.html"})
@MultipartConfig
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "EcommerceUsersJPA",name = "jpa/pc")
	private EntityManager em;
	

	private ServletContext servletContext;
>>>>>>> dev
       
	// Hash table of RequestHandler instances, keyed by request URL
	private Map<String,IHandler> handlerHash = new HashMap<String,IHandler>();
	
<<<<<<< HEAD
	public ControllerServlet() {
        super();
    }


	// Initialize mappings: not implemented here
	public void init() throws ServletException {
		
    	//appContext = config.getServletContext();
		
	    // This will read mapping definitions and populate handlerHash
	    handlerHash.put("/profile.html", new ShowProfileHandler());
	    handlerHash.put("/index.html", new ShowProductHandler());
	    handlerHash.put("/modifyUser.html", new ModifyProfileHandler());
	    handlerHash.put("/insert_product.html", new InsertProductHandler());
	    handlerHash.put("/shop.html", new ShowAllProductsHandler());
	    handlerHash.put("/loggingin.html", new LoginRequestHandler());
	    handlerHash.put("/registering.html", new RegisterRequestHandler());	 
	    handlerHash.put("/loggingout.html", new LoginRequestHandler());	 
	    handlerHash.put("/wishlist.html", new WishlistRequestHandler());	
	    handlerHash.put("/cart.html", new CartRequestHandler());
	    handlerHash.put("/product.html", new ShowProductHandler());
	    handlerHash.put("/add_to_cart.html", new AddCartRequestHandler());


	    
	    servletContext = getServletConfig().getServletContext();
=======
	  // Initialize mappings: not implemented here
	public void init() throws ServletException {
	
	    // This will read mapping definitions and populate handlerHash
		handlerHash.put("/profile.html", new ShowProfileHandler());
		handlerHash.put("/index.html", new ShowProductHandler());
		handlerHash.put("/modifyUser.html", new ModifyProfileHandler());
		handlerHash.put("/insert_product.html", new InsertProductHandler());
		handlerHash.put("/shop.html", new ShowAllProductsHandler());

		servletContext = getServletConfig().getServletContext();
>>>>>>> dev
	    setServletContextUtils();
	    
	  }
	
<<<<<<< HEAD
	private void setServletContextUtils() {
		
		System.out.println("DENTRO DE SET SERVLET CONTEXT UTILS");
		CategoryManager categoryManager = new CategoryManager();
		List<List<Category>> categoryTree = categoryManager.findCategoryTree();
		servletContext.setAttribute("categoryTree", categoryTree);
		System.out.println("CATEGORY TREEEEEEEEEE SIZE: " + categoryTree.size());
	}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		System.out.println("DO GET FRONT CONTROLLER");

	    // Retrieve from the HashMap the instance of the class which implements the logic of the requested url
	    IHandler rh = (IHandler) handlerHash.get(request.getServletPath());
	    // If no instance is retrieved redirects to error
	    if (rh == null) {
		    response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    } else {
	    	
		    // Call the method handleRequsest of the instance in order to obtain the url 
		    String viewURL = rh.handleRequest(request, response);
		    // Dispatch the request to the url obtained
		    System.out.println("En el Handler: mandando a la vista " + viewURL);
		    if (viewURL == null) {
			    // nothing
		    } else {
			    System.out.println("Redirecting to URL: " + viewURL);
			    request.getRequestDispatcher(viewURL).forward(request, response);
		    }
	    }

    }
	  
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		doGet(request,response);
		  
	}
	  
=======
	  
	private void setServletContextUtils() {
		CategoryManager categoryManager = new CategoryManager();
		List<List<Category>> categoryTree = categoryManager.findCategoryTree();
		servletContext.setAttribute("categoryTree", categoryTree);
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
>>>>>>> dev
}