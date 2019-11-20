package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import es.uc3m.ecommerce.manager.*;
import es.uc3m.ecommerce.model.*;

/*
 * handler que gestiona la modificacion o borrado de productos (vendedor)
*/
public class ModifyProductHandler implements IHandler {
	
	private boolean modifyOrDelete; //true->modify false->delete
	
	public ModifyProductHandler (boolean modifyOrDelete) {
		this.modifyOrDelete = modifyOrDelete;
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		return modifyOrDelete ? processModify(request) : processDelete(request);
	}

	
	public String processModify(HttpServletRequest request) 
			throws ServletException, IOException {
		ProductManager im = new ProductManager();
		CategoryManager ca = new CategoryManager();
		
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
		
		product.setCategoryBean(ca.findByName(productCategory));

		//foto no obligatoria: comprobamos que existe para realizar el update en bd
		String control = request.getParameter("fileToUpLoad");
		if(control==null) {
			 Part filePart = request.getPart("fileToUpLoad");
			 byte[] data = new byte[(int) filePart.getSize()];
			 filePart.getInputStream().read(data, 0, data.length);
			 
			 product.setProductPicture(data);
		}
		
		try {
			//una vez con los parametros actualizados, llamamos al manager para realizar la operacion en bd
			im.modifyProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		request.setAttribute("product", product);
		
		return "product_list_seller.html";
	}
	
	public String processDelete(HttpServletRequest request) 
			throws ServletException, IOException {
		
		ProductManager im = new ProductManager();
		//recoger parametros. contador sirve para saber que producto de la lista es
		int counter=Integer.parseInt(request.getParameter("contadorBorr"));
		HttpSession mySession = request.getSession(true);
		Product product = (Product)mySession.getAttribute("productToDelete"+counter);
	
		//no borramos de base de datos, sino que cambiamos el flag
		product.setIsDeleted(1);	
		
		try {
			im.modifyProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return "product_list_seller.html";
	
	}

}