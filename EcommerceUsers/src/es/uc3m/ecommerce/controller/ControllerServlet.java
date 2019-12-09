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

/*
 * Servlet que se encarga de escuchar todas las URL que acaban en .html y llamar al handler correspondiente, 
 * que redirigira a la vista a cargar, después de haber realizado las operaciones necesarias
 * 
*/

@WebServlet(urlPatterns = {"*.html"})
@MultipartConfig
public class ControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ServletContext servletContext; //usamos el servletcontext para cargar la vista de categorias al principio, cuando se carga el index.html
	
	@PersistenceContext(unitName = "EcommerceUsersPU",name = "jpa/pc")
	private EntityManager em;
       
	// Mapa con los handlers que getionan las acciones a realizar por cada html 
	private Map<String,IHandler> handlerHash = new HashMap<String,IHandler>();
	//JMS: escucha cualquier mensaje, y cuando recibe uno de tipo confirmación, realiza las acciones necesarias	
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
			e.printStackTrace();
		}

		
	    // Esto popula handlerHash y es utilizado para instanciar el handler correspondiente al path de la request captada
		
		
		//handlers para registro, login, logout
	    handlerHash.put("/loggingin.html", new LoginRequestHandler());
	    handlerHash.put("/registering.html", new RegisterRequestHandler());	 
	    handlerHash.put("/loggingout.html", new LoginRequestHandler());	 

	    //acciones del usuario registrado
	    handlerHash.put("/profile.html", new ShowProfileHandler());
	    handlerHash.put("/modifyUser.html", new ModifyProfileHandler(true));
	    handlerHash.put("/deleteUser.html", new ModifyProfileHandler(false));
	    handlerHash.put("/product_list_seller.html", new ShowMyProductListHandler());
	    handlerHash.put("/insert_product.html", new InsertProductHandler());
	    handlerHash.put("/modify_product.html", new ModifyProductHandler(true));
	    handlerHash.put("/deleteProduct.html", new ModifyProductHandler(false));
	    handlerHash.put("/modif_product.html", new ShowProductForModifyHandler());   
	    
	    //acciones relativas a la compra
	    handlerHash.put("/shop.html", new ShowAllProductsHandler());
	    handlerHash.put("/search.html", new SearchHandler());
	    handlerHash.put("/advanced_search.html", new AdvancedSearchHandler());
	    handlerHash.put("/product.html", new ShowProductHandler());
	    
	    //getsiona la confirmación de compra
	    handlerHash.put("/sendOrderMessage.html", new SendOrderMessageHandler());

	    // Carrito
	    handlerHash.put("/cart.html", new CartRequestHandler());
	    handlerHash.put("/remove_from_cart.html", new CartRequestHandler());
	    handlerHash.put("/add_to_cart.html", new CartRequestHandler());
	    handlerHash.put("/edit_cart.html", new CartRequestHandler());
	    handlerHash.put("/insert_purchase.html", new InsertPurchaseHandler());

	    // Wishlist 
	    handlerHash.put("/wishlist.html", new WishlistRequestHandler());	
	    handlerHash.put("/add_to_wishlist.html", new WishlistRequestHandler());

	    // Handlers para los mensajes
	    handlerHash.put("/sendMessageToSeller.html", new SendMessageHandler(true));
	    handlerHash.put("/sendMessageBroadcast.html", new SendMessageHandler(false));
	    handlerHash.put("/readMessage.html", new ReadMessageHandler());
	    handlerHash.put("/readBrowserMessage.html", new ReadBrowserMessageHandler());
	    handlerHash.put("/purchase_list.html", new ShowAllPurchasesHandler());
	    handlerHash.put("/purchase.html", new ShowPurchaseHandler());
	    handlerHash.put("/showMsg1to1.html", new ShowMsg1to1Handler(false));    
	    handlerHash.put("/sendeMessageToSeller.html", new ShowMsg1to1Handler(true));

	    //popula la vista de categorias del header cuando se inicia la app (antes de llamar a cualquier página)
	    servletContext = getServletConfig().getServletContext();
	    servletContext.setAttribute("categoryTree", getCategoryTree());
	  }
	
	private List<List<Category>> getCategoryTree() {
		CategoryManager categoryManager = new CategoryManager();
		return categoryManager.findCategoryTree();
	}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Coger del HashMap la instancia de la clase que implementa la lÃ³gica para la URL recibida
	    IHandler rh = (IHandler) handlerHash.get(request.getServletPath());
	    // Si no hay instancia asociada a la URL, error
	    if (rh == null) {
		    response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    } else {	    	
		    // Llamada al metodo handleRequsest de la instancia para obtener la URL a la que hay que redireccionar
		    String viewURL = rh.handleRequest(request, response);
		    if (viewURL == null) {
			    // No hacer nada
		    } else {
		    	//Arbol de categorias en request por si hay algun cambio desde admin, que coja la version mas actualizada
			    request.setAttribute("categoryTree", getCategoryTree());
			    //Redirige a la vista correspondiente, devuelta por el handler
			    request.getRequestDispatcher(viewURL).forward(request, response);
		    }
	    }
    }
	  
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  	doGet(request,response); //redirige al post
	 }
	  
	 public void destroy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JMSException {
		 msgListener.stop(); //al terminar con el listener, se para
	 }
}