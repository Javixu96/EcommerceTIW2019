package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uc3m.ecommerce.model.*;

/*
* Handler para añadir un producto nuevo
*/
public class InsertProductHandler implements IHandler {

	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;	
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:13100");
		String path = "products";
		webTargetPath = webTarget.path(path);
		
		//Obtener el usuario de la sesion
		HttpSession session = request.getSession();
		Appuser appuser = (Appuser) session.getAttribute("user");
		
		//Crear un nuevo producto
		Product product = new Product();

		//Asignarle las propiedades recogidos por los input de la vista
		String productName = request.getParameter("product_name");
		String shortDescription = request.getParameter("pShortDesc");
		String longDescription = request.getParameter("pLongDesc");
		int price = Integer.parseInt(request.getParameter("product_price"));
		int stock = Integer.parseInt(request.getParameter("product_stock"));
		
		product.setProductName(productName);
		product.setShortDesc(shortDescription);
		product.setLongDesc(longDescription);
		product.setPrice(price);
		product.setStock(stock);
		
		//foto
		Part filePart = request.getPart("fileToUpload");
	    byte[] data = new byte[(int) filePart.getSize()];
	    filePart.getInputStream().read(data, 0, data.length);
		product.setProductPicture(data);
	    
		//categoria
	    String productCategory= request.getParameter("subcategory");
	    product.setCategoryBean(findCategoryByName(productCategory));
	    
	    //el propietario o seller
		product.setAppuser(appuser);
		
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
		resp= invocationBuilder.post(Entity.entity(product,MediaType.APPLICATION_JSON));

		return "product_list_seller.html";
	}
	
	private Category findCategoryByName(String productCategory) {
		String path = "categories";
		webTarget = client.target("http://localhost:13100");		
		webTargetPath = webTarget.path(path);
		invocationBuilder = webTargetPath.queryParam("categoryName", productCategory).request(MediaType.APPLICATION_JSON);
		Category category = null;
		
		// Invocar al servicio
		resp = invocationBuilder.get();
		if (resp.getStatus() == 200) {
			category = resp.readEntity(Category.class);
		} 
		
		return category;
	}
}
