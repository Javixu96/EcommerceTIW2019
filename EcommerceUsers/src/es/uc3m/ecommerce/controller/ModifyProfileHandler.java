package es.uc3m.ecommerce.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.uc3m.ecommerce.manager.*;
import es.uc3m.ecommerce.model.*;

public class ModifyProfileHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserManager im = new UserManager();
		
		String userName = request.getParameter("name");
		String userSurnames = request.getParameter("surnames");
		String userEmail = request.getParameter("email");
		String userDirection = request.getParameter("direction");
		
		Appuser user =im.getUserById(3);
		
		user.setUserName(userName);
		user.setUserSurnames(userSurnames);
		user.setEmail(userEmail);
		user.setPostalAddress(userDirection);

		
		try {
			im.modifyUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// response.sendRedirect("controlador");
		
		UserManager userManager = new UserManager();
		//HttpSession sesion = request.getSession();
		
		Appuser appuser = userManager.getUserById(3);
				
		request.setAttribute("user", appuser);		
		
		return "profile.jsp";
	}

}