package es.uc3m.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchExactlyHandler implements IHandler {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("name");
		
	/*	// You can select JTA or WihoutJTA commenting one of these code lines
		PersonaManagerJTA pm = new PersonaManagerJTA();
		//PersonaManagerWithoutJTA pm = new PersonaManagerWithoutJTA();
		
		List<Person> l=pm.findByName(name);
		
		req.setAttribute("list", l);
		*/
		return "people.jsp";
	}

}
