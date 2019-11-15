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
import es.uc3m.ecommerce.model.Appuser;


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
	
	public List<Appuser> findAll() {
		
		List<Appuser> userList;
		
		Query query = em.createNamedQuery("Appuser.findAll");
		userList = query.getResultList();

		return userList;

	}
	
	public List<Appuser> findByEmail(String email) {
		
		//EntityManager em = emf.createEntityManager();
		List<Appuser> userList;
		System.out.println("The email that is going to be check in DB against: "+ email);
		
		// With JNDI surround with try/catch is not neccesary
		Query q= em.createNamedQuery("Appuser.findByEmail");
		q.setParameter("email", email);
		userList = q.getResultList();
		
		return userList;
			
	}
	
	public void insert(String introducedName, String introducedSurname, 
		String introducedAddress, String introducedEmail, String introducedPassword, int introducedRole) {
		
		System.out.println("USER MANAGER - INSERT");

		
		Appuser u = new Appuser();
		u.setEmail(introducedEmail);
		u.setIsDeleted(0);
		u.setPostalAddress(introducedAddress);
		u.setPw(introducedPassword);
		u.setUserName(introducedName);
		u.setUserSurnames(introducedSurname);
		u.setUserRole(introducedRole);
	
		//EntityManager em = emf.createEntityManager();

		try {
			ut.begin();
			
			System.out.println("Will add a record to DB: ");
			System.out.println(u.getEmail());
			System.out.println(u.getPw());
			System.out.println(u.getIsDeleted());
			System.out.println(u.getUserId());
			System.out.println(u.getUserName());
			System.out.println(u.getUserSurnames());
			System.out.println(u.getPostalAddress());
			System.out.println(u.getUserRole());
			
			em.persist(u);
			
			ut.commit();
		} catch (Exception e) {
			if(ut!= null)
				try {
					System.out.println("Exception but ut is not null. Going to rollback");
					ut.rollback();
				} catch (Exception e1) {
					System.out.println("Rollback exception");
					e1.printStackTrace();
				} 
		}
	}
	
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
		
		System.out.println("USER MANAGER - MODIFY USER");

		
		System.out.println("Going to modify a user record, the new values are: ");
		System.out.println(user.getEmail());
		System.out.println(user.getPw());
		System.out.println(user.getIsDeleted());
		System.out.println(user.getUserId());
		System.out.println(user.getUserName());
		System.out.println(user.getUserSurnames());
		System.out.println(user.getPostalAddress());
		System.out.println(user.getUserRole());
		
		try {
			ut.begin();
			em.merge(user);
			ut.commit();
		} catch (Exception e) {
			if(ut!= null)
				try {
					System.out.println("Exception but ut is not null. Going to rollback");
					ut.rollback();
				} catch (Exception e1) {
					System.out.println("Rollback exception");
					e1.printStackTrace();
				} 
		}	

	}
}
