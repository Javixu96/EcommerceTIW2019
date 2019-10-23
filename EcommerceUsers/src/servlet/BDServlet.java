package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

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
@WebServlet({ "/BDServlet", "*.html" })
public class BDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName="laboratoriosPU")
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


	// Establece el Content Type
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();

	out.println("<HTML>");
	out.println("<HEAD><TITLE>BDServlet</TITLE></HEAD>");
	out.println("<BODY bgcolor=\"#ffff66\">");
	out.println("<H1><FONT color=\"#666600\">Database: Ecommerce</FONT></H1></BR>");
	out.println("<FORM METHOD=\"POST\" ACTION=\"" + "\">"); // Se llama as� mismo por POST  
	
	try {
		ut.begin();
		
		out.println("Dentro de try <br>");
		
		/*
		 * Esto funciona
		 * 	 String jpql = "SELECT p.productName FROM Product p";
		 *   Query products = em.createQuery(jpql);
		 *   List<String> results = products.getResultList();
		 */
		String jpql = "SELECT p.productName FROM Product p WHERE p.productId = ?1";
		Query products = em.createQuery(jpql);
		products.setParameter(1, 3);
		products.setMaxResults(1);
				
		// Query query = em.createQuery(jpql);
		// query.setParameter(1, 1);
		// query.setMaxResults(10);
		
		
		List<String> results = products.getResultList();
		
		for (String productName : results){
			out.println("<FONT color=\"#ff0000\">"+productName+"</FONT><BR>");
		}
		
		ut.commit();
	
		
	} catch (Exception e) {
		out.println("<FONT color=\"#ff0000\">"+e.getMessage()+"</FONT><BR>");
	}
	
	
/*
	try {
		String jpql = "SELECT * FROM Product";
		Query customers = em.createQuery(jpql);
		List results = customers.getResultList();
		out.println("<FONT color=\"#ff0000\">"+ results.get(0) +"</FONT><BR>");
			
	} catch (Exception e) {
		out.println("<FONT color=\"#ff0000\">"+e.getMessage()+"</FONT><BR>");
	}
*/


	out.println("</FORM>");
	out.println("</BODY></HTML>");

	out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
