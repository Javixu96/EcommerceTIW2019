package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.uc3m.ecommerce.manager.*;
import es.uc3m.ecommerce.model.*;

public class ModifyProductHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductManager im = new ProductManager();
		CategoryManager ca = new CategoryManager();
		
		String productName = request.getParameter("name");
		String productShortDesc = request.getParameter("shortDesc");
		String productLongDesc = request.getParameter("longDesc");
		String productCategory = request.getParameter("subcategory");
		int productPrice = Integer.parseInt(request.getParameter("price"));
		int productStock = Integer.parseInt(request.getParameter("stock"));
		
		
		Product product =im.findById(8);
		
		product.setProductName(productName);
		product.setShortDesc(productShortDesc);
		product.setLongDesc(productLongDesc);
		product.setPrice(productPrice);
		product.setStock(productStock);
	
		System.out.println("asdasdas"+productCategory);
		
		List<Category> categoryList=ca.findByName(request.getParameter(productCategory));
		for (Category category : categoryList){
			product.setCategoryBean(category);
			System.out.println("asdasdas"+category.getCategoryName());
		}
		
		
		if(request.getPart("file")!=null) {
			 Part filePart = request.getPart("file");
			 byte[] data = new byte[(int) filePart.getSize()];
			 filePart.getInputStream().read(data, 0, data.length);
			 
			 product.setProductPicture(data);
		}
		
		try {
			im.modifyProduct(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// response.sendRedirect("controlador");
		
		Product products = im.findById(8);
		
		request.setAttribute("product", products);
		
	    Category subCategory = products.getCategoryBean();
	    
	    Category category = subCategory.getCategory();
	    
		request.setAttribute("productCategory", category);
		
		request.setAttribute("productSubcategory", subCategory);	
		
		
		return "modif_product.jsp";
	}

}
