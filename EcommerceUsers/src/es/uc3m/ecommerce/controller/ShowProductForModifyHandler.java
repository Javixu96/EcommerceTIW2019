package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import es.uc3m.ecommerce.manager.CategoryManager;
import es.uc3m.ecommerce.manager.ProductManager;
import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.ecommerce.model.Product;
import es.uc3m.ecommerce.model.Category;

public class ShowProductForModifyHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductManager productManager = new ProductManager();
		//HttpSession sesion = request.getSession();
		
		Product products = productManager.findById(8);
		
		request.setAttribute("product", products);
		
	    Category subCategory = products.getCategoryBean();
	    
	    Category category = subCategory.getCategory();
	    
		request.setAttribute("productCategory", category);
		
		request.setAttribute("productSubcategory", subCategory);
		
		
		
		return "modif_product.jsp";
	}

}