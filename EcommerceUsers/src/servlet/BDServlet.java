package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

/**
 * Servlet implementation class BDServlet
 */
@WebServlet({ "/BDServlet", "*.html"})

public class BDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext (unitName="EcommerceUsersJPA")
	EntityManager em;
	
	@Resource
	UserTransaction ut;
	////////////////////////////////////////////////////////////////////////////////////////
	public void init() {

		// Lee del contexto de servlet (Sesi�n a nivel de aplicaci�n)
		ServletContext context = getServletContext();
	}


	////////////////////////////////////////////////////////////////////////////////////////
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws IOException, ServletException {
/*

	// Establece el Content Type
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();

	out.println("<HTML>");
	out.println("<HEAD><TITLE>BDServlet</TITLE></HEAD>");
	out.println("<BODY bgcolor=\"#ffff66\">");
	out.println("<H1><FONT color=\"#666600\">Database: Users</FONT></H1></BR>");
	out.println("<FORM METHOD=\"POST\" ACTION=\"" + "\">"); // Se llama as mismo por POST        


	try {	
		ut.begin();
		
		Appuser u = new Appuser();
		/*
		u.setName("Javi2");
		u.setSurnames("Xu2");
		u.setAddress("1111");
		u.setEmail("11111111");
		u.setImageURL("111111");
		u.setPw("11111");
		u.setIsAdmin(0);
	
		em.persist(u);
		
		out.println(u.getName());
		ut.commit();
		
	
	} catch (Exception e) {
		out.println("<FONT color=\"#ff0000\">"+e.getMessage()+"</FONT><BR>");
	}



	out.println("</FORM>");
	out.println("</BODY></HTML>");

	out.close();
	*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String auxObject = request.getServletPath();
		 
		 if(auxObject.compareTo("/profile.jsp")==0) {
			 try {	
					ut.begin();
					
					Appuser u = new Appuser();
					/*
					u.setName("Javi2");
					u.setSurnames("Xu2");
					u.setAddress("1111");
					u.setEmail("11111111");
					u.setImageURL("111111");
					u.setPw("11111");
					u.setIsAdmin(0);
					*/
					/*Fomulario de profile*/
					
					u.setName((String) request.getParameter("name"));
					u.setSurnames((String) request.getParameter("surnames"));
				
					em.persist(u);					
					
					ut.commit();
					
					request.setAttribute("user", u);
					
					request.getRequestDispatcher("profile.jsp").forward(request, response);
				} catch (Exception e) {
			
				}
			 
		 }
		

	}

}
