package es.uc3m.ecommerce.manager;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;

public class ProductManager {

	private EntityManager em;
	private UserTransaction ut;
	
	public ProductManager() {
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
	
	
	public String create(Product product) throws Exception {
		try {
			ut.begin();
			em.persist(product);
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
		return "";
	}
	
	// Esta anotaci¨®n es para quitar el warning avisandonos que es est¨¢
	// haciendo una conversi¨®n de List a List<Product> y puede no ser v¨¢lida
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		List<Product> resultado;
		
		Query query = em.createNamedQuery("Product.findAll",Product.class);
		resultado = query.getResultList();
		
		return resultado;

	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findBySimilarTitle(String titulo) {
		List<Product> resultado;

		Query query = em.createNamedQuery("Product.findBySimilarTitle",Product.class);
		// Atenci¨®n: Se neceista agregar el % porque se usa una consutla con like (buscar en google)
		query.setParameter("titulo","%"+titulo+"%");
		resultado = query.getResultList();

		return resultado;

	}
	
	public Product findById(int id) {
		Product resultado;
	
		resultado  = em.find(Product.class, id);
		
		return resultado;
	}
	
	public void removeById(Product product) throws Exception {
		try {
			ut.begin();
			em.remove(product);
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
	
	public void modifyProduct(Product product) throws Exception {
		
			ut.begin();
			em.merge(product);
			ut.commit();

		return;

	}	
}
