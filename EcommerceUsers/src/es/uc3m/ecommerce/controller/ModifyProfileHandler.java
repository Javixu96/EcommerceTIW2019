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
	
	
	//true->modify false->delete
	private boolean modifyOrDelete;
	
	public ModifyProfileHandler (boolean modifyOrDelete) {
		this.modifyOrDelete = modifyOrDelete;
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return modifyOrDelete ? processModify(request) : processDelete(request);
	}
	
	
	public String processModify(HttpServletRequest request)
			throws ServletException, IOException { 
		// TODO Auto-generated method stub
		UserManager im = new UserManager();
		
		String userName = request.getParameter("profile_name");
		String userSurnames = request.getParameter("profile_surnames");
		String userEmail = request.getParameter("profile_email");
		String userAddress = request.getParameter("profile_address");
		
		
		HttpSession session = request.getSession();
		Appuser user = (Appuser) session.getAttribute("user");

		user.setUserName(userName);
		user.setUserSurnames(userSurnames);
		user.setEmail(userEmail);
		user.setPostalAddress(userAddress);
		
		Part filePart = request.getPart("fileToUpLoad");
		if((int) filePart.getSize()!=0) {
			 byte[] data = new byte[(int) filePart.getSize()];
			 filePart.getInputStream().read(data, 0, data.length);
			 
			 user.setUserPicture(data);
		}
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String newPwRepeat = request.getParameter("newPwRepeat");
		
		if(!oldPassword.isEmpty()) {
			if(!oldPassword.equals(user.getPw())) {
				request.setAttribute("invalidCredentialsError", 1);
			}else {
				if(!newPassword.equals(newPwRepeat)) {
					request.setAttribute("invalidRepeatError", 1);
				}else {
					user.setPw(newPassword);
				}
			}
		}else {
			request.setAttribute("invalidCredentialsError", null);
			request.setAttribute("invalidRepeatError", null);
		}
		

		// Update DB and Session attribute -> both or none
		// Check if this way this is accomplished
		try {
			im.modifyUser(user);
			session.setAttribute("user", user);
			// Display success message to user 
			request.setAttribute("modifyUserSuccess", 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return "profile.html";
	}
	
	
	public String processDelete(HttpServletRequest request) { 
		
		UserManager im = new UserManager();
		HttpSession session = request.getSession();
		Appuser user = (Appuser) session.getAttribute("user");
		
		user.setIsDeleted(1);
		
		try {
			im.modifyUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index.jsp";
	}

}