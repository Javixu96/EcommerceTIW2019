package servlet;

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
	private int userid;

	private String address;

	private String email;

	private String imageurl;

	private int isAdmin;

	private int isSeller;

	private String name;

	private String postalAddress;

	private String pw;

	private String userName;

	@Lob
	private byte[] userPicture;

	private String userSurnames;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="appuser1")
	private List<Product> products1;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="appuser2")
	private List<Product> products2;

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
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public String getUserSurnames() {
		return this.userSurnames;
	}

	public void setUserSurnames(String userSurnames) {
		this.userSurnames = userSurnames;
	}

	public List<Product> getProducts1() {
		return this.products1;
	}

	public void setProducts1(List<Product> products1) {
		this.products1 = products1;
	}

	public Product addProducts1(Product products1) {
		getProducts1().add(products1);
		products1.setAppuser1(this);

		return products1;
	}

	public Product removeProducts1(Product products1) {
		getProducts1().remove(products1);
		products1.setAppuser1(null);

		return products1;
	}

	public List<Product> getProducts2() {
		return this.products2;
	}

	public void setProducts2(List<Product> products2) {
		this.products2 = products2;
	}

	public Product addProducts2(Product products2) {
		getProducts2().add(products2);
		products2.setAppuser2(this);

		return products2;
	}

	public Product removeProducts2(Product products2) {
		getProducts2().remove(products2);
		products2.setAppuser2(null);

		return products2;
	}

}