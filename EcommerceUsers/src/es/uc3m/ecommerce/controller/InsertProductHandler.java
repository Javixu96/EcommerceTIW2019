package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import es.uc3m.ecommerce.manager.*;
import es.uc3m.ecommerce.model.*;

public class InsertProductHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductManager im = new ProductManager();
		CategoryManager ca = new CategoryManager();
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
	    product.setCategoryBean(ca.findByName(productCategory));
	    
	    //el propietario o seller
		product.setAppuser(appuser);
		
		//crear el producto con persist()
		try {
			im.create(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "product_list_seller.html";
	}

}
