package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.uc3m.ecommerce.manager.*;
import es.uc3m.ecommerce.model.*;

public class InsertProductHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductManager im = new ProductManager();
		UserManager us = new UserManager();
		CategoryManager ca = new CategoryManager();
		
		
		Product product = new Product();
		// Getting the image (file)
		Part filePart = request.getPart("fileToUpload");

		String productName = request.getParameter("product_name");
		String shortDescription = request.getParameter("pShortDesc");
		String longDescription = request.getParameter("pLongDesc");
		int price = Integer.parseInt(request.getParameter("product_price"));
		int stock = Integer.parseInt(request.getParameter("product_stock"));

	    byte[] data = new byte[(int) filePart.getSize()];
	    filePart.getInputStream().read(data, 0, data.length);
	    // i2.setTitulo(request.getParameter("titulo"));
	   
	    
	    Category pSubcategory = new Category();
	    pSubcategory = ca.findById(5);
	    
	    Appuser appuser = new Appuser();
	    appuser=us.getUserById(3);
	    
		product.setProductName(productName);
		product.setShortDesc(shortDescription);
		product.setLongDesc(longDescription);
		product.setCategoryBean(pSubcategory);
		product.setProductPicture(data);
		product.setAppuser(appuser);
		product.setPrice(price);
		product.setStock(stock);
		
		try {
			im.create(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// response.sendRedirect("controlador");
		return "add_product.jsp";
	}

}