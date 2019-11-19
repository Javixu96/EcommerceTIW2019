package es.uc3m.ecommerce.controller;


import java.io.IOException;

import javax.jms.JMSException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;

import es.uc3m.ecommerce.manager.CategoryManager;
import es.uc3m.ecommerce.model.Category;

import java.util.Map;
import java.util.HashMap;
import java.util.List;


@WebServlet(urlPatterns = {"*.html"})
@MultipartConfig
public class ControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ServletContext servletContext;
	
	@PersistenceContext(unitName = "EcommerceUsersPU",name = "jpa/pc")
	private EntityManager em;
       
	// Hash table of RequestHandler instances, keyed by request URL
	private Map<String,IHandler> handlerHash = new HashMap<String,IHandler>();
	private StartMessageListener msgListener;
	
	public ControllerServlet() {
        super();
    }


	// Initialize mappings: not implemented here
	public void init() throws ServletException {
		msgListener = new StartMessageListener();
		try {
			msgListener.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
    	//appContext = config.getServletContext();
		
	    // This will read mapping definitions and populate handlerHash
	    handlerHash.put("/loggingin.html", new LoginRequestHandler());
	    handlerHash.put("/registering.html", new RegisterRequestHandler());	 
	    handlerHash.put("/loggingout.html", new LoginRequestHandler());	 

	    handlerHash.put("/profile.html", new ShowProfileHandler());
	    handlerHash.put("/modifyUser.html", new es.uc3m.ecommerce.controller.ModifyProfileHandler(true));
	    handlerHash.put("/deleteUser.html", new es.uc3m.ecommerce.controller.ModifyProfileHandler(false));
	    handlerHash.put("/product_list_seller.html", new es.uc3m.ecommerce.controller.ShowMyProductListHandler());
	    handlerHash.put("/insert_product.html", new es.uc3m.ecommerce.controller.InsertProductHandler());
	    handlerHash.put("/modify_product.html", new es.uc3m.ecommerce.controller.ModifyProductHandler(true));
	    handlerHash.put("/deleteProduct.html", new es.uc3m.ecommerce.controller.ModifyProductHandler(false));
	    handlerHash.put("/modif_product.html", new es.uc3m.ecommerce.controller.ShowProductForModifyHandler());    
	    handlerHash.put("/shop.html", new ShowAllProductsHandler());
	    handlerHash.put("/search.html", new SearchHandler());
	    handlerHash.put("/product.html", new ShowProductHandler());
	    handlerHash.put("/wishlist.html", new WishlistRequestHandler());	
	    handlerHash.put("/cart.html", new CartRequestHandler());
	    handlerHash.put("/add_to_cart.html", new AddCartRequestHandler());
	    handlerHash.put("/sendOrderMessage.html", new SendOrderMessageHandler());

	    // handlerHash.put("/sendMessages.html", new es.uc3m.ecommerce.controller.SendMessageQueueHandler());
	    handlerHash.put("/sendMessageToSeller.html", new SendMessageHandler());
	    handlerHash.put("/readMessage.html", new ReadMessageHandler());
	    handlerHash.put("/readBrowserMessage.html", new ReadBrowserMessageHandler());
	    handlerHash.put("/purchase_list.html", new ShowAllPurchasesHandler());
	    handlerHash.put("/purchase.html", new ShowPurchaseHandler());
	    handlerHash.put("/showMsg1to1.html", new ShowMsg1to1Handler());
	    

	    servletContext = getServletConfig().getServletContext();
	    servletContext.setAttribute("categoryTree", getCategoryTree());
	  }
	
	private List<List<Category>> getCategoryTree() {
		CategoryManager categoryManager = new CategoryManager();
		return categoryManager.findCategoryTree();
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
			    request.setAttribute("categoryTree", getCategoryTree());
			    System.out.println("Arbol de categorias en request, con # elementos: " + getCategoryTree().size());
			    request.getRequestDispatcher(viewURL).forward(request, response);
		    }
	    }

    }
	  
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  	doGet(request,response);
	 }
	  
	 public void destroy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JMSException {
		 msgListener.stop();
	 }
}