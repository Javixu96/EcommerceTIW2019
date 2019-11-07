package es.uc3m.ecommerce.manager;

import java.util.List;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.model.Product;
<<<<<<< HEAD
=======
import es.uc3m.ecommerce.model.Appuser;
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef

public class UserManager {
	
	private EntityManager em;
	private UserTransaction ut;
	
	public UserManager() {
		try {
			Context ctx = new InitialContext();
			/*
			 * The java:comp/env/jpa/pc has been defined at Servlet Class.
			 * The Servlet has done the injection using the following lines and then it has published at "jpa/pc".
			 * @PersistenceContext(unitName = "AgendaPersistentUnitWithJTA",name = "jpa/pc")
			 * private EntityManager em;
			 */
			em = (EntityManager) ctx.lookup("java:comp/env/jpa/pc");
			/*
			 * // You can get the UserTransaction using injection via @Resource (Only in injectable class such as Servlet or EJB).
			 * // You can use this anotation until The DynamicWebProject 3.1
			 * // If you want to use it in 4.0, you need to follow this instructions:
			 * // http://balusc.omnifaces.org/2013/10/how-to-install-cdi-in-tomcat.html
			 * @Resource
			 * private UserTransaction ut;  
			 */
			ut = (UserTransaction) ctx.lookup("java:comp/UserTransaction");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Product> getAllProducts() {
		
			
			System.out.println("Dentro de try <br>");
			

			String jpql = "SELECT p FROM Product p";
			Query products = em.createQuery(jpql);
			List<Product> results = products.getResultList();
			
			/*
			for (Product product : results){
				System.out.println("<FONT color=\"#ff0000\">"+product.getProductName()+"</FONT><BR>");
			}
			*/
		
		return results;
	}
	
	public Product getElementById(int id) {
		
			System.out.println("Dentro de try <br>");
			

			/*String jpql = "SELECT p"
					+ " FROM Product p "
					+ " WHERE p.productid = ?1";
			Query products = em.createQuery(jpql);
			products.setParameter(1, id);
			products.setMaxResults(1);
					
			List<String> results = products.getResultList();
			*/
	
			Product p =em.find(Product.class, id);
			
			return p;
		
	}
<<<<<<< HEAD
=======
	
	public Appuser getUserById(int id) {
		
		System.out.println("Dentro de try <br>");
		

		/*String jpql = "SELECT p"
				+ " FROM Product p "
				+ " WHERE p.productid = ?1";
		Query products = em.createQuery(jpql);
		products.setParameter(1, id);
		products.setMaxResults(1);
				
		List<String> results = products.getResultList();
		*/

		Appuser p =em.find(Appuser.class, id);
		
		return p;
	
}
	
	
	public void modifyUser(Appuser user) throws Exception {
		try {
			ut.begin();
			em.merge(user);
			ut.commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return;

	
}	
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef
}
