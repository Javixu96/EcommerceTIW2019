package es.uc3m.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the appusers database table.
 * 
 */
@Entity
@Table(name="appusers")
@NamedQuery(name="Appuser.findAll", query="SELECT a FROM Appuser a")
public class Appuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userid;

	private String address;

	private String email;

	private String imageurl;

	private int isAdmin;

	private int isSeller;

	private String name;
=======
	private int userId;

	private String email;

	private int isDeleted;
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef

	private String postalAddress;

	private String pw;

	private String userName;

	@Lob
	private byte[] userPicture;

<<<<<<< HEAD
	private String userSurnames;

	//bi-directional many-to-onide association to Product
	//@OneToMany(mappedBy="sellerId")
	//private List<Product> products;

	public Appuser() {
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
=======
	private int userRole;

	private String userSurnames;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="appuser")
	private List<Product> products;

	//bi-directional many-to-one association to Purchas
	@OneToMany(mappedBy="appuser")
	private List<Purchas> purchases;

	public Appuser() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

<<<<<<< HEAD
	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public int getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getIsSeller() {
		return this.isSeller;
	}

	public void setIsSeller(int isSeller) {
		this.isSeller = isSeller;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
=======
	public int getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef
	}

	public String getPostalAddress() {
		return this.postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPw() {
		return this.pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public byte[] getUserPicture() {
		return this.userPicture;
	}

	public void setUserPicture(byte[] userPicture) {
		this.userPicture = userPicture;
	}

<<<<<<< HEAD
=======
	public int getUserRole() {
		return this.userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef
	public String getUserSurnames() {
		return this.userSurnames;
	}

	public void setUserSurnames(String userSurnames) {
		this.userSurnames = userSurnames;
	}

<<<<<<< HEAD
	/*public List<Product> getProducts() {
		return this.products;
	}*/

	/*public void setProducts(List<Product> products) {
=======
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setAppuser(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setAppuser(null);

		return product;
<<<<<<< HEAD
	}*/
=======
	}

	public List<Purchas> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(List<Purchas> purchases) {
		this.purchases = purchases;
	}

	public Purchas addPurchas(Purchas purchas) {
		getPurchases().add(purchas);
		purchas.setAppuser(this);

		return purchas;
	}

	public Purchas removePurchas(Purchas purchas) {
		getPurchases().remove(purchas);
		purchas.setAppuser(null);

		return purchas;
	}
>>>>>>> 1f108551fb5e345f49ebe512fced0271a33fb1ef

}