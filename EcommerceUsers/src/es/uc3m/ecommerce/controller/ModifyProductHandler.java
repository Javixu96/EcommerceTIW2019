package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

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
 * handler que gestiona la modificacion o borrado de productos (vendedor)
*/
public class ModifyProductHandler implements IHandler {
	
	private boolean modifyOrDelete; //true->modify false->delete
	Client client;
	WebTarget webTarget;
	WebTarget webTargetPath;
	Invocation.Builder invocationBuilder;
	Response resp;	
	
	public ModifyProductHandler (boolean modifyOrDelete) {
		this.modifyOrDelete = modifyOrDelete;
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		client = ClientBuilder.newClient();
		return modifyOrDelete ? processModify(request) : processDelete(request);
	}

	
	public String processModify(HttpServletRequest request) 
			throws ServletException, IOException {
		webTarget = client.target("http://localhost:13100");
		String path = "products";
		webTargetPath = webTarget.path(path);
		
		//recoger parametros del form
		String productName = request.getParameter("name");
		String productShortDesc = request.getParameter("shortDesc");
		String productLongDesc = request.getParameter("longDesc");
		String productCategory = request.getParameter("subcategory");
		int productPrice = Integer.parseInt(request.getParameter("price"));
		int productStock = Integer.parseInt(request.getParameter("stock"));
		
		HttpSession mySession = request.getSession(true);
		Product product=(Product)mySession.getAttribute("productInModify");
		
		//popular producto con nuevos valores
		product.setProductName(productName);
		product.setShortDesc(productShortDesc);
		product.setLongDesc(productLongDesc);
		product.setPrice(productPrice);
		product.setStock(productStock);
		
		product.setCategoryBean(findCategoryByName(productCategory));

		//foto no obligatoria: comprobamos que existe para realizar el update en bd
		String control = request.getParameter("fileToUpLoad");
		if(control==null) {
			 Part filePart = request.getPart("fileToUpLoad");
			 byte[] data = new byte[(int) filePart.getSize()];
			 filePart.getInputStream().read(data, 0, data.length);
			 
			 product.setProductPicture(data);
		}
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
		resp= invocationBuilder.put(Entity.entity(product,MediaType.APPLICATION_JSON));

		request.setAttribute("product", product);
		
		return "product_list_seller.html";
	}
	
	public String processDelete(HttpServletRequest request) 
			throws ServletException, IOException {
		
		webTarget = client.target("http://localhost:13100");

		//recoger parametros. contador sirve para saber que producto de la lista es
		int counter=Integer.parseInt(request.getParameter("contadorBorr"));
		HttpSession mySession = request.getSession(true);
		Product product = (Product)mySession.getAttribute("productToDelete"+counter);

		String path = "products"+"/"+product.getProductId();
		webTargetPath = webTarget.path(path);
		invocationBuilder = webTargetPath.request(MediaType.APPLICATION_JSON);	
		resp= invocationBuilder.delete();
		
	
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
