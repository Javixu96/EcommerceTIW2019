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

/*
* Handler que gestiona el envío de mensajes 1 a 1
*/
public class ShowMsg1to1Handler implements IHandler {
	
	//true-accedido desde la pagina de productos, false- desde las notificaciones
	private boolean sellerOrTo1;
	
	public ShowMsg1to1Handler (boolean sellerOrTo1) {
		this.sellerOrTo1 = sellerOrTo1;
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		return sellerOrTo1 ? processToSeller(request,res) : processTo1(request);
	}


	public String processToSeller(HttpServletRequest request,HttpServletResponse res){
		
		String viewURL = null; 
		//recogemos el usuario logueado
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") == null) {	
			
			try {
				viewURL = new ForbiddenPageHandler().handleRequest(request, res);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return viewURL;
		}else {
			return "messages_1to1.jsp";
		}
	}
	
	public String processTo1(HttpServletRequest request){
		//contador para saber cual es el otro usuario en chat
		int counter=Integer.parseInt(request.getParameter("contadorMsg"));
		//recogemos el usuario logueado
		HttpSession session = request.getSession(true);
		
		Appuser user = (Appuser) session.getAttribute("sender"+counter);
		
		session.setAttribute("sender", user);
	
		return "readMessage.html";
	}
}