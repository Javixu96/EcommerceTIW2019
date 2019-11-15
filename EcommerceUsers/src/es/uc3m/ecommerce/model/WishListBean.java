package es.uc3m.ecommerce.model;

import java.util.List;

import javax.servlet.http.HttpSession;

public class WishListBean {

	private HttpSession session; 
	private Appuser user;
	private List<Product> products;
	
	public WishListBean() {
		
	}
	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public Appuser getUser() {
		return user;
	}
	public void setUser(Appuser user) {
		this.user = user;
		
		//this.user = session.getAttribute("user");
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public Product addProduct() {
		
		
		return null;
	}
	
	
	
}
