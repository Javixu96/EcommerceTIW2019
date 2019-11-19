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

public class ModifyProfileHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("MODIFY PROFILE HANDLER");

		
		// TODO Auto-generated method stub
		UserManager im = new UserManager();
		
		String userName = request.getParameter("profile_name");
		String userSurnames = request.getParameter("profile_surnames");
		String userEmail = request.getParameter("profile_email");
		String userAddress = request.getParameter("profile_address");
		
		System.out.println("The values of the form are ");
		System.out.println(userName);
		System.out.println(userSurnames);
		System.out.println(userEmail);
		System.out.println(userAddress);
		
		
		HttpSession session = request.getSession();
		Appuser user = (Appuser) session.getAttribute("user");
		
		System.out.println("The old values of the user are: ");
		System.out.println(user.getEmail());
		System.out.println(user.getPw());
		System.out.println(user.getUserName());
		System.out.println(user.getUserSurnames());
		System.out.println(user.getPostalAddress());

		user.setUserName(userName);
		user.setUserSurnames(userSurnames);
		user.setEmail(userEmail);
		user.setPostalAddress(userAddress);
		
		System.out.println("The new values of the user are: ");
		System.out.println(user.getEmail());
		System.out.println(user.getPw());
		System.out.println(user.getUserName());
		System.out.println(user.getUserSurnames());
		System.out.println(user.getPostalAddress());
		

		// Update DB and Session attribute -> both or none
		// Check if this way this is accomplished
		try {
			im.modifyUser(user);
			session.setAttribute("user", user);
			// Display success message to user 
			request.setAttribute("modifyUserSuccess", 1);
			// Send again on a request attribute the user's new data
			// Can be accessed through the form parameters or through the new Appuser object
			// I decided to do it through the Appuser object for security reasons
			
			request.setAttribute("userName", user.getUserName());	
			request.setAttribute("userSurnames", user.getUserSurnames());
			request.setAttribute("userAddress", user.getPostalAddress());
			request.setAttribute("userEmail", user.getEmail());	
			request.setAttribute("userPw", user.getPw());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "profile.jsp";
	}

}