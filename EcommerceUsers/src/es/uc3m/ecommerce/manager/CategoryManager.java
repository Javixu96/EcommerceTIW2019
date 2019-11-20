package es.uc3m.ecommerce.manager;


import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.model.Category;
import es.uc3m.ecommerce.model.Product;

/*
* Clase que se encarga de las operaciones contra la tabla Categories en BD
*/
public class CategoryManager {

	private EntityManager em;
	private UserTransaction ut;
	
	public CategoryManager() {
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
			e.printStackTrace();
		}
	}
	
	/*
	* Crea un "ärbol" de categorias para pintar el header y la columna en shop.jsp
	*/
	@SuppressWarnings("unchecked")
	public List<List<Category>> findCategoryTree(){
		List<List<Category>> categoryTree = new LinkedList<List<Category>>();

		Query q1 = em.createNativeQuery("SELECT c.categoryId FROM Categories c WHERE c.categoryId in (SELECT DISTINCT c.parentId FROM Categories c) AND c.parentId IS NOT NULL;");
		List<Integer> categoryParents = q1.getResultList();

		int index = 0;
		
		for (Integer c : categoryParents) {
			List<Category> children = new LinkedList<>();
			Query q2 = em.createNativeQuery("Select c.categoryId FROM Categories c WHERE c.parentId = " + c);
			q2.setParameter("parentId", c);
			List<Integer> categoryChildren = q2.getResultList();
			
			for (Integer c2 : categoryChildren) {
				children.add(findById(c2));
			}
			categoryTree.add(index, children);
			index++;
		}
		
		return categoryTree;
	}
	
	
	public Category findById(int id) {
		Category resultado;

		resultado  = em.find(Category.class, id);

		return resultado;
	}
	
	
	public Category findByName(String name) {
		Query q= em.createNamedQuery("Category.findByName");
		
		q.setParameter("categoryName", name);
		
		Category c;
		
		try {
			c =  (Category) q.getSingleResult();		
		} catch (NoResultException n){
			c = null;
		}
		
		return c;

	}
}